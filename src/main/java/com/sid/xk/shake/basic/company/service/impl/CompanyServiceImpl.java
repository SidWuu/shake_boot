package com.sid.xk.shake.basic.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private IBillCodeService billCodeService;

    @Override
    public Page<BasicCompany> queryPage(CompanyQuery form) {
        BasicCompany company = new BasicCompany();
        BeanUtils.copyProperties(form, company);
        company.setCompanyStatus(BaseConstants.STATUS_0);
        company.setIsDel(BaseConstants.STATUS_0);
        QueryWrapper<BasicCompany> query = new QueryWrapper<BasicCompany>().setEntity(company);
        Page<BasicCompany> page = new Page<>(form.getCurrent(), form.getSize());
        return page(page, query);
    }

    @Override
    public List<BasicCompanyLinkman> queryDetail(String companyCode) {
        Objects.requireNonNull(companyCode, "参数为空");
        return companyLinkmanService.lambdaQuery().eq(BasicCompanyLinkman::getCompanyCode, companyCode).list();
    }

    @Override
    public CompanyBean getBean(String companyCode) {
        Objects.requireNonNull(companyCode, "参数为空");
        BasicCompany main = lambdaQuery().eq(BasicCompany::getCompanyCode, companyCode).one();
        Objects.requireNonNull(main, "企业信息不存在");
        List<BasicCompanyLinkman> details = queryDetail(companyCode);
        CompanyBean bean = new CompanyBean();
        bean.setMain(main);
        bean.setDetails(details);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
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
        // 校验重复
        BasicCompany check = lambdaQuery().eq(BasicCompany::getCompanyName, main.getCompanyName()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s]企业已存在", main.getCompanyName()));
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
        saveDetail(bean, BaseConstants.DATA_FLAG_0);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(CompanyBean bean) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(bean.getMain(), "参数为空");
        BasicCompany main = bean.getMain();
        // 校验企业
        String msg = checkMain(main);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        BasicCompany old = lambdaQuery().eq(BasicCompany::getCompanyCode, main.getCompanyCode()).one();
        Objects.requireNonNull(old, "企业信息不存在");
        // 校验重复
        BasicCompany check = lambdaQuery().eq(BasicCompany::getCompanyName, main.getCompanyName()).ne(BasicCompany::getCompanyCode, main.getCompanyCode()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s]企业已存在", main.getCompanyName()));
        }
        // 更新企业
        boolean success = true;
        try {
            main.setId(old.getId());
            main.setCompanyCode(old.getCompanyCode());
            main.setLastUpdateTime(new Date());
            success = updateById(main);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新失败");
        }
        // 更新联系人(可能新增, 修改, 删除)
        saveDetail(bean, BaseConstants.DATA_FLAG_1);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(String companyCode) {
        Objects.requireNonNull(companyCode, "参数为空");
        // 删除企业
        boolean success = true;
        BasicCompany old = lambdaQuery().eq(BasicCompany::getCompanyCode, companyCode).one();
        Objects.requireNonNull(old, "企业信息不存在");
        if (StringUtil.equals(BaseConstants.STATUS_1, old.getIsDel())) {
            BaseException.throwException("已删除");
        }
        try {
            old.setIsDel(BaseConstants.STATUS_1);
            old.setLastUpdateTime(new Date());
            success = updateById(old);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("删除失败");
        }
        // 删除联系人
        try {
            success = companyLinkmanService.remove(new LambdaQueryWrapper<BasicCompanyLinkman>().eq(BasicCompanyLinkman::getCompanyCode, companyCode));
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("删除联系人失败");
        }
    }

    /**
     * 保存明细
     * @param bean 保存信息
     * @param dataFlag 操作标志
     */
    private void saveDetail(CompanyBean bean, Integer dataFlag) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(dataFlag, "参数为空");
        List<BasicCompanyLinkman> details = bean.getDetails();
        if (null != details && !details.isEmpty()) {
            List<BasicCompanyLinkman> inserts = new ArrayList<>();
            List<BasicCompanyLinkman> updates = new ArrayList<>();
            List<Long> deleteIds = new ArrayList<>();
            for (BasicCompanyLinkman detail : details) {
                if (BaseConstants.DATA_FLAG_0.equals(detail.getDataFlag())) {
                    // 新增
                    detail.setCompanyCode(bean.getMain().getCompanyCode());
                    detail.setLinkmanCode(billCodeService.getMaxCode("basic_company_linkman", "linkman_code"));
                    String msg = checkDetail(detail);
                    if (StringUtil.isNotEmpty(msg)) {
                        BaseException.throwException(msg);
                    }
                    BasicCompanyLinkman check = companyLinkmanService.lambdaQuery().eq(BasicCompanyLinkman::getLinkmanName, detail.getLinkmanName()).eq(BasicCompanyLinkman::getLinkmanPhone, detail.getLinkmanPhone()).one();
                    if (null != check) {
                        BaseException.throwException(String.format("[%s/%s]联系人已存在", detail.getLinkmanName(), detail.getLinkmanPhone()));
                    }
                    inserts.add(detail);
                } else if (BaseConstants.DATA_FLAG_1.equals(detail.getDataFlag())) {
                    // 修改
                    String msg = checkDetail(detail);
                    if (StringUtil.isNotEmpty(msg)) {
                        BaseException.throwException(msg);
                    }
                    BasicCompanyLinkman old = companyLinkmanService.lambdaQuery().eq(BasicCompanyLinkman::getLinkmanCode, detail.getLinkmanCode()).one();
                    Objects.requireNonNull(old, "联系人已不存在");
                    BasicCompanyLinkman check = companyLinkmanService.lambdaQuery().eq(BasicCompanyLinkman::getLinkmanName, detail.getLinkmanName()).eq(BasicCompanyLinkman::getLinkmanPhone, detail.getLinkmanPhone()).ne(BasicCompanyLinkman::getLinkmanCode, detail.getLinkmanCode()).one();
                    if (null != check) {
                        BaseException.throwException(String.format("[%s/%s]联系人已存在", detail.getLinkmanName(), detail.getLinkmanPhone()));
                    }
                    detail.setId(old.getId());
                    detail.setCompanyCode(old.getCompanyCode());
                    detail.setLinkmanCode(old.getLinkmanCode());
                    updates.add(detail);
                } else if (BaseConstants.DATA_FLAG_2.equals(detail.getDataFlag())) {
                    // 删除
                    BasicCompanyLinkman old = companyLinkmanService.lambdaQuery().eq(BasicCompanyLinkman::getLinkmanCode, detail.getLinkmanCode()).one();
                    Objects.requireNonNull(old, "联系人已不存在");
                    deleteIds.add(detail.getId());
                } else {
                    BaseException.throwException(String.format("无法识别操作标志[%s]", dataFlag));
                }
            }
            saveDetails(inserts);
            updateDetails(updates);
            deleteDetails(deleteIds);
        }
    }

    private void saveDetails(List<BasicCompanyLinkman> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = companyLinkmanService.saveBatch(details, details.size());
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存联系人失败");
        }
    }

    private void updateDetails(List<BasicCompanyLinkman> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = companyLinkmanService.updateBatchById(details, details.size());
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新联系人失败");
        }
    }

    private void deleteDetails(List<Long> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = companyLinkmanService.removeByIds(details);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新联系人失败");
        }
    }

    private void setDefault(BasicCompany company) {
        if (StringUtil.isEmpty(company.getCompanyCode())) {
            company.setCompanyCode(billCodeService.getMaxCode("basic_company", "company_code"));
        }
        Date date = new Date();
        company.setCreateTime(date);
        company.setLastUpdateTime(date);
        company.setCompanyStatus(BaseConstants.STATUS_0);
        company.setIsDel(BaseConstants.STATUS_0);
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
        msg += StringUtil.emptyToMsg(linkman.getLinkmanCode(), "代码为空");
        msg += StringUtil.emptyToMsg(linkman.getLinkmanName(), "姓名为空");
        msg += StringUtil.emptyToMsg(linkman.getLinkmanPhone(), "电话为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
