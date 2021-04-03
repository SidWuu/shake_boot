package com.sid.xk.shake.basic.company.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.service.ICompanyService;
import com.sid.xk.shake.basic.company.vo.CompanyBean;
import com.sid.xk.shake.common.constants.StatusEnum;
import com.sid.xk.shake.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_DATA;
import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_MESSAGE;
import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_STATUS;


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
    public ModelMap query(@RequestBody BasicCompany form, @RequestBody Page<BasicCompany> page) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<BasicCompany> data = companyService.queryPage(form, page);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PostMapping("/save")
    public ModelMap save(@RequestBody CompanyBean bean) {
        ModelMap modelMap = new ModelMap();
        try {
            companyService.insert(bean);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }


}
