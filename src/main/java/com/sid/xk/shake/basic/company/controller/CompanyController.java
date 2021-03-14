package com.sid.xk.shake.basic.company.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaodong
 * @date 2020/09/13
 */
@RestController
@RequestMapping("company/*")
public class CompanyController {

    @GetMapping("sayHello")
    public ModelMap sayHello() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("HelloWorld!");
        return modelMap;
    }

    @GetMapping("area")
    public ModelMap query() {
        ModelMap modelMap = new ModelMap();

        return modelMap;
    }

}
