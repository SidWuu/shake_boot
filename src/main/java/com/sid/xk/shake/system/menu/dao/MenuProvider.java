package com.sid.xk.shake.system.menu.dao;

import com.sid.xk.shake.system.menu.vo.MenuQuery;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author wuxiaodong
 * @date 2021/03/14
 */
public class MenuProvider {

    private static final String tableName = "system_menu a";

    public String querySql(Map<String, Object> params) {
        MenuQuery form = (MenuQuery) params.get("form");

        SQL sql = new SQL();
        sql.SELECT("a.menu_id as menuId, a.menu_code as menuCode, a.menu_name as menuName, a.menu_path as menuPath, a.menu_icon as menuIcon, a.menu_parent as menuParent, a.sort as sort, a.menu_status as menuStatus, a.is_del as isDel, a.remark as remark")
                .FROM(tableName);

        return sql.toString();
    }

}
