package com.sid.xk.shake.basic.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 物资代码表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 物资代码
     */
    private String productCode;

    /**
     * 品名大类
     */
    private String productParent;

    /**
     * 品名
     */
    private String productName;

    /**
     * 规格
     */
    private String productSpec;

    /**
     * 材质
     */
    private String productMaterial;

    /**
     * 产地
     */
    private String productArea;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 备用字段1
     */
    private String col1;

    /**
     * 备用字段2
     */
    private String col2;

    /**
     * 备用字段3
     */
    private String col3;

    /**
     * 备用字段4
     */
    private String col4;

    /**
     * 备用字段5
     */
    private String col5;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductParent() {
        return productParent;
    }

    public void setProductParent(String productParent) {
        this.productParent = productParent;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
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
        return "BasicProduct{" +
            "id=" + id +
            ", productCode=" + productCode +
            ", productParent=" + productParent +
            ", productName=" + productName +
            ", productSpec=" + productSpec +
            ", productMaterial=" + productMaterial +
            ", productArea=" + productArea +
            ", imagePath=" + imagePath +
            ", col1=" + col1 +
            ", col2=" + col2 +
            ", col3=" + col3 +
            ", col4=" + col4 +
            ", col5=" + col5 +
            ", dataStatus=" + dataStatus +
            ", isDel=" + isDel +
            ", createTime=" + createTime +
            ", lastUpdateTime=" + lastUpdateTime +
            ", remark=" + remark +
        "}";
    }
}
