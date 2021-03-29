package com.sid.xk.shake.basic.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 仓位表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@TableName("basic_warearea")
public class Warearea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库位代码
     */
    private String wareareaCode;

    /**
     * 仓库代码
     */
    private String warehouseCode;

    /**
     * 库位名称
     */
    private String wareareaName;

    /**
     * 备注
     */
    private String remark;

    public String getWareareaCode() {
        return wareareaCode;
    }

    public void setWareareaCode(String wareareaCode) {
        this.wareareaCode = wareareaCode;
    }
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    public String getWareareaName() {
        return wareareaName;
    }

    public void setWareareaName(String wareareaName) {
        this.wareareaName = wareareaName;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Warearea{" +
            "wareareaCode=" + wareareaCode +
            ", warehouseCode=" + warehouseCode +
            ", wareareaName=" + wareareaName +
            ", remark=" + remark +
        "}";
    }
}
