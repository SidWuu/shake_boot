package com.sid.xk.shake.system.menu.service;

import com.sid.xk.shake.model.SystemMenu;
import com.sid.xk.shake.system.menu.vo.MenuQuery;

import java.util.List;

/**
 * @author wuxiaodong
 * @date 2021/03/14
 */
public interface MenuService {

    /**
     * @param form 查询条件
     * @return List<SystemMenu>
     */
    List<SystemMenu> query(MenuQuery form);

}
