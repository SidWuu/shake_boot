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
import com.sid.xk.shake.common.constants.BaseConstants;
import com.sid.xk.shake.common.exception.BaseException;
import com.sid.xk.shake.common.utils.StringUtil;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    private IBillRuleService billRuleService;

    @Override
    public Page<BasicCompany> queryPage(BasicCompany form, Page<BasicCompany> page) {
        QueryWrapper<BasicCompany> query = new QueryWrapper<>();
        query.setEntity(form);
        return baseMapper.selectPage(page, query);
    }

    @Override
    public void insert(CompanyBean bean) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(bean.getMain(), "参数为空");
        BasicCompany company = bean.getMain();
        // 默认值
        setDefault(company);
        // 校验企业
        String msg = checkMain(company);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 保存企业
        boolean success = true;
        try {
            success = save(company);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
        // 保存联系人
        List<BasicCompanyLinkman> linkmen = bean.getDetails();
        if (null != linkmen && !linkmen.isEmpty()) {
            for (BasicCompanyLinkman linkman : linkmen) {
                // 校验联系人
            }
            try {
                success = companyLinkmanService.saveBatch(linkmen, linkmen.size());
            } catch (RuntimeException e) {
                BaseException.throwException(e.getMessage());
            }
            if (!success) {
                BaseException.throwException("保存联系人失败");
            }
        }
    }

    private void setDefault(BasicCompany company) {
        if (StringUtil.isEmpty(company.getCompanyCode())) {
//            QueryWrapper<BasicCompany> queryWrapper = new QueryWrapper<>();
//            queryWrapper.orderByDesc("company_code").last("limit 1");
//            BasicCompany old = baseMapper.selectOne(queryWrapper);
            // TODO 自增代码
            company.setCompanyCode("0001");
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

    @Override
    public void update(CompanyBean bean) {

    }

    @Override
    public void delete(CompanyBean bean) {

    }
}
