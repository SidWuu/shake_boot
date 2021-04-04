package com.sid.xk.shake.system.rule.entity;

import java.io.Serializable;

/**
 * <p>
 * 单据代码规则表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-04
 */
public class SystemBillRule implements Serializable {

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
     * 代码字段长度
     */
    private Integer codeLength;

    /**
     * 代码前缀
     */
    private String codePrefix;

    /**
     * 包含时间 0是 1否
     */
    private Integer isDate;

    /**
     * 时间格式
     */
    private String dateFormat;

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
    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }
    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }
    public Integer getIsDate() {
        return isDate;
    }

    public void setIsDate(Integer isDate) {
        this.isDate = isDate;
    }
    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SystemBillRule{" +
            "id=" + id +
            ", tableName=" + tableName +
            ", codeColumn=" + codeColumn +
            ", codeLength=" + codeLength +
            ", codePrefix=" + codePrefix +
            ", isDate=" + isDate +
            ", dateFormat=" + dateFormat +
            ", remark=" + remark +
        "}";
    }
}
