package com.sid.xk.shake.mapper;

import com.sid.xk.shake.model.SystemMenu;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKeyWithBLOBs(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);
}