package com.sid.xk.shake.basic.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.entity.BasicCompanyLinkman;
import com.sid.xk.shake.basic.company.mapper.CompanyMapper;
import com.sid.xk.shake.basic.company.service.ICompanyLinkmanService;
import com.sid.xk.shake.basic.company.service.ICompanyService;
import com.sid.xk.shake.basic.company.vo.CompanyBean;
import com.sid.xk.shake.basic.company.vo.CompanyQuery;
import com.sid.xk.shake.common.constants.BaseConstants;
import com.sid.xk.shake.common.exception.BaseException;
import com.sid.xk.shake.common.utils.StringUtil;
import com.sid.xk.shake.system.rule.service.IBillCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 往来企业表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, BasicCompany> implements ICompanyService {

    @Autowired
    private ICompanyLinkmanService companyLinkmanService;
    @Autowired
    private IBillCodeService billCodeService;

    @Override
    public Page<BasicCompany> queryPage(CompanyQuery form) {
        QueryWrapper<BasicCompany> query = new QueryWrapper<>();
        Page<BasicCompany> page = new Page<>(form.getCurrent(), form.getSize());
        return baseMapper.selectPage(page, query);
    }

    @Override
    public CompanyBean getBean(String companyCode) {
        if (StringUtil.isEmpty(companyCode)) {
            BaseException.throwException("参数为空");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("company_code", companyCode);
        BasicCompany main = getMain(params);
        Objects.requireNonNull(main, "企业信息不存在");
        List<BasicCompanyLinkman> details = companyLinkmanService.list(new QueryWrapper<BasicCompanyLinkman>().allEq(params));
        CompanyBean bean = new CompanyBean();
        bean.setMain(main);
        bean.setDetails(details);
        return bean;
    }

    @Override
    public void insert(CompanyBean bean) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(bean.getMain(), "参数为空");
        BasicCompany main = bean.getMain();
        // 默认值
        setDefault(main);
        // 校验企业
        String msg = checkMain(main);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 保存企业
        boolean success = true;
        try {
            success = save(main);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
        // 保存联系人
        List<BasicCompanyLinkman> details = bean.getDetails();
        if (null != details && !details.isEmpty()) {
            for (BasicCompanyLinkman detail : details) {
                // 校验联系人
                detail.setCompanyCode(main.getCompanyCode());
                detail.setLinkmanCode(billCodeService.getMaxCode("basic_company_linkman", "linkman_code"));
                msg = checkDetail(detail);
                if (StringUtil.isNotEmpty(msg)) {
                    BaseException.throwException(msg);
                }
            }
            try {
                success = companyLinkmanService.saveBatch(details, details.size());
            } catch (RuntimeException e) {
                BaseException.throwException(e.getMessage());
            }
            if (!success) {
                BaseException.throwException("保存联系人失败");
            }
        }
    }

    @Override
    public void update(CompanyBean bean) {

    }

    @Override
    public void delete(CompanyBean bean) {

    }

    private BasicCompany getMain(Map<String, Object> params) {
        return getOne(new QueryWrapper<BasicCompany>().allEq(params));
    }

    private BasicCompanyLinkman getDetail(Map<String, Objects> params) {
        return companyLinkmanService.getOne(new QueryWrapper<BasicCompanyLinkman>().allEq(params));
    }

    private void setDefault(BasicCompany company) {
        if (StringUtil.isEmpty(company.getCompanyCode())) {
            company.setCompanyCode(billCodeService.getMaxCode("basic_company", "company_code"));
        }
        Date date = new Date();
        company.setCreateTime(date);
        company.setLastUpdateTime(date);
    }

    private String checkMain(BasicCompany company) {
        String msg = "";
        msg += StringUtil.emptyToMsg(company.getCompanyCode(), "代码为空");
        msg += StringUtil.emptyToMsg(company.getCompanyName(), "名称为空");
        msg += StringUtil.emptyToMsg(company.getCompanyAbbreviation(), "简称为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

    private String checkDetail(BasicCompanyLinkman linkman) {
        String msg = "";
        msg += StringUtil.emptyToMsg(linkman.getCompanyCode(), "代码为空");
        msg += StringUtil.emptyToMsg(linkman.getLinkmanName(), "姓名为空");
        msg += StringUtil.emptyToMsg(linkman.getLinkmanPhone(), "电话为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
