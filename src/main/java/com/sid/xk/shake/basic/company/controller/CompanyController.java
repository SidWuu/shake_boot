package com.sid.xk.shake.basic.company.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.service.ICompanyService;
import com.sid.xk.shake.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 往来企业表 前端控制器
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/basic/company")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    @PostMapping("/pages")
    public ModelMap query(BasicCompany form, Page<BasicCompany> page) {
        ModelMap modelMap = new ModelMap();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }


}
