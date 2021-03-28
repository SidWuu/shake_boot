package com.sid.xk.shake.system.menu.dao;

import com.sid.xk.shake.mapper.SystemMenuMapper;
import com.sid.xk.shake.model.SystemMenu;
import com.sid.xk.shake.system.menu.vo.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface MenuDao extends SystemMenuMapper, Serializable {

    static final String resultMap = "com.sid.xk.shake.dao.SystemMenuMapper.ResultMapWithBLOBs";

    @SelectProvider(value = MenuProvider.class, method = "querySql")
    @ResultType(List.class)
    List<SystemMenu> query(@Param("form") MenuQuery form);
}
