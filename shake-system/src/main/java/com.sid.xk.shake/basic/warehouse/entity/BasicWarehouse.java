package com.sid.xk.shake.basic.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 仓库表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 仓库代码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 仓库联系人
     */
    private String warehouseLinkman;

    /**
     * 仓库电话
     */
    private String warehousePhone;

    /**
     * 地址
     */
    private String warehouseAddress;

    /**
     * 状态 0启用 1禁用
     */
    private Integer warehouseStatus;

    /**
     * 删除 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date lastUpdateTime;

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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseLinkman() {
        return warehouseLinkman;
    }

    public void setWarehouseLinkman(String warehouseLinkman) {
        this.warehouseLinkman = warehouseLinkman;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Integer getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(Integer warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
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
        return "BasicWarehouse{" +
            "id=" + id +
            ", warehouseCode=" + warehouseCode +
            ", warehouseName=" + warehouseName +
            ", warehouseLinkman=" + warehouseLinkman +
            ", warehousePhone=" + warehousePhone +
            ", warehouseAddress=" + warehouseAddress +
            ", warehouseStatus=" + warehouseStatus +
            ", isDel=" + isDel +
            ", createTime=" + createTime +
            ", lastUpdateTime=" + lastUpdateTime +
            ", remark=" + remark +
        "}";
    }
}
