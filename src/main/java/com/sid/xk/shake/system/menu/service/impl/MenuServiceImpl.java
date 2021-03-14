package com.sid.xk.shake.system.menu.service.impl;

import com.sid.xk.shake.model.SystemMenu;
import com.sid.xk.shake.system.menu.dao.MenuDao;
import com.sid.xk.shake.system.menu.service.MenuService;
import com.sid.xk.shake.system.menu.vo.MenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuxiaodong
 * @date 2021/03/14
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<SystemMenu> query(MenuQuery form) {
        return menuDao.query(form);
    }
}
