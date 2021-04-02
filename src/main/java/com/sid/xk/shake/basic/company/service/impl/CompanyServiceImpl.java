package com.sid.xk.shake.basic.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.mapper.CompanyMapper;
import com.sid.xk.shake.basic.company.service.ICompanyService;
import org.springframework.stereotype.Service;

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


    @Override
    public Page<BasicCompany> queryPage(BasicCompany form, Page<BasicCompany> page) {
        QueryWrapper<BasicCompany> query = new QueryWrapper<>();
        query.select("");

        return null;
    }
}
