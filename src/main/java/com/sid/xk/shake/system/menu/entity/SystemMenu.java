package com.sid.xk.shake.system.menu.entity;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-10
 */
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 父级菜单
     */
    private Long menuParent;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 0启用 1禁用
     */
    private Integer menuStatus;

    /**
     * 删除 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    public Long getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(Long menuParent) {
        this.menuParent = menuParent;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SystemMenu{" +
            "id=" + id +
            ", menuCode=" + menuCode +
            ", menuName=" + menuName +
            ", menuPath=" + menuPath +
            ", menuIcon=" + menuIcon +
            ", menuParent=" + menuParent +
            ", sort=" + sort +
            ", menuStatus=" + menuStatus +
            ", isDel=" + isDel +
            ", remark=" + remark +
        "}";
    }
}
