package com.sid.xk.shake.basic.dictionary.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 类型
     */
    private String dataType;

    /**
     * 编码
     */
    private String dataCode;

    /**
     * 值
     */
    private String dataValue;

    /**
     * 状态 0启用 1禁用
     */
    private Integer dataStatus;

    /**
     * 删除 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近修改时间
     */
    private LocalDateTime lastUpdateTime;

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
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }
    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BasicDictionary{" +
            "id=" + id +
            ", dataType=" + dataType +
            ", dataCode=" + dataCode +
            ", dataValue=" + dataValue +
            ", dataStatus=" + dataStatus +
            ", isDel=" + isDel +
            ", createTime=" + createTime +
            ", lastUpdateTime=" + lastUpdateTime +
            ", remark=" + remark +
        "}";
    }
}
