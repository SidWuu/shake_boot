package com.sid.xk.shake.basic.company.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.vo.CompanyBean;

/**
 * <p>
 * 往来企业表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
public interface ICompanyService extends IService<BasicCompany> {


    /**
     * 分页查询
     * @param form 查询条件
     * @param page 分页
     * @return Page<Company>
     */
    Page<BasicCompany> queryPage(BasicCompany form, Page<BasicCompany> page);

    /**
     * 新增
     * @param bean 保存bean
     */
    void insert(CompanyBean bean);

    /**
     * 修改
     * @param bean 保存bean
     */
    void update(CompanyBean bean);

    /**
     * 删除
     * @param bean 保存bean
     */
    void delete(CompanyBean bean);
}
