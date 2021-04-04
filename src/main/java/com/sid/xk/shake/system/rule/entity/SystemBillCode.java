package com.sid.xk.shake.system.rule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 单据最大代码表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-04
 */
public class SystemBillCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 代码字段
     */
    private String codeColumn;

    /**
     * 时间格式
     */
    private String dateFormat;

    /**
     * 日期时间
     */
    private Date dateTime;

    /**
     * 当前代码
     */
    private String maxCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getCodeColumn() {
        return codeColumn;
    }

    public void setCodeColumn(String codeColumn) {
        this.codeColumn = codeColumn;
    }
    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public String getMaxCode() {
        return maxCode;
    }

    public void setMaxCode(String maxCode) {
        this.maxCode = maxCode;
    }

    @Override
    public String toString() {
        return "SystemBillCode{" +
            "id=" + id +
            ", tableName=" + tableName +
            ", codeColumn=" + codeColumn +
            ", dateFormat=" + dateFormat +
            ", dateTime=" + dateTime +
            ", maxCode=" + maxCode +
        "}";
    }
}
