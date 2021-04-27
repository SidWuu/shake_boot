package com.sid.xk.shake.basic.company.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.entity.BasicCompanyLinkman;
import com.sid.xk.shake.basic.company.service.ICompanyService;
import com.sid.xk.shake.basic.company.vo.CompanyBean;
import com.sid.xk.shake.basic.company.vo.CompanyQuery;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.StatusEnum;
import com.sid.xk.shake.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public ModelMap query(@RequestBody CompanyQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<BasicCompany> data = companyService.queryPage(form);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/detail/{companyCode}")
    public ModelMap queryDetail(@PathVariable String companyCode) {
        ModelMap modelMap = new ModelMap();
        try {
            List<BasicCompanyLinkman> data = companyService.queryDetail(companyCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/edit/{companyCode}")
    public ModelMap edit(@PathVariable String companyCode) {
        ModelMap modelMap = new ModelMap();
        try {
            CompanyBean data = companyService.getBean(companyCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PostMapping("/save")
    public ModelMap save(@RequestBody CompanyBean bean) {
        ModelMap modelMap = new ModelMap();
        try {
            companyService.insert(bean);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, bean.getMain().getCompanyCode());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PutMapping("/update")
    public ModelMap update(@RequestBody CompanyBean bean) {
        ModelMap modelMap = new ModelMap();
        try {
            companyService.update(bean);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @DeleteMapping("/delete/{companyCode}")
    public ModelMap delete(@PathVariable String companyCode) {
        ModelMap modelMap = new ModelMap();
        try {
            companyService.delete(companyCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

}
