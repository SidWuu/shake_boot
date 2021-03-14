package com.sid.xk.shake.system.menu.controller;

import com.sid.xk.shake.common.enums.ReturnState;
import com.sid.xk.shake.model.SystemMenu;
import com.sid.xk.shake.system.menu.service.MenuService;
import com.sid.xk.shake.system.menu.vo.MenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wuxiaodong
 * @date 2021/03/14
 */

@RestController
@RequestMapping("menu/*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("query")
    public ModelMap query(MenuQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            List<SystemMenu> list = menuService.query(form);
            modelMap.addAttribute(ReturnState.SUCCESS);
            modelMap.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }

}
