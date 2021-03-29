package com.sid.xk.shake.basic.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 仓库表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@TableName("basic_warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private LocalDateTime createTime;

    /**
     * 最近修改时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 备注
     */
    private String remark;

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
        return "Warehouse{" +
            "warehouseCode=" + warehouseCode +
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
