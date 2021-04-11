package com.sid.xk.shake.basic.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sid.xk.shake.common.constants.BaseConstants;

import java.io.Serializable;

/**
 * <p>
 * 仓位表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicWarearea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 非数据库字段 操作标记 0新增 1修改 2删除
     */
    @TableField(exist = false)
    private Integer dataFlag = BaseConstants.DATA_FLAG_1;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "BasicWarearea{" +
                "id=" + id +
                ", wareareaCode=" + wareareaCode +
                ", warehouseCode=" + warehouseCode +
                ", wareareaName=" + wareareaName +
                ", remark=" + remark +
                "}";
    }
}
