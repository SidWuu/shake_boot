package com.sid.xk.shake.system.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.system.menu.entity.SystemMenu;
import com.sid.xk.shake.system.menu.mapper.MenuMapper;
import com.sid.xk.shake.system.menu.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SystemMenu> implements IMenuService {

}
