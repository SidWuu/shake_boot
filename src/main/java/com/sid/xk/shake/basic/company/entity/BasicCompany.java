package com.sid.xk.shake.basic.company.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 往来企业表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 企业代码
     */
    private String companyCode;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业简称
     */
    private String companyAbbreviation;

    /**
     * 企业类型_数据字典
     */
    private String companyType;

    /**
     * 税号_社会统一信用代码
     */
    private String companyTax;

    /**
     * 省份
     */
    private String areaProvince;

    /**
     * 城市
     */
    private String areaCity;

    /**
     * 区县
     */
    private String areaCounty;

    /**
     * 企业地址
     */
    private String companyAddress;

    /**
     * 企业电话
     */
    private String companyPhone;

    /**
     * 企业传真
     */
    private String companyFax;

    /**
     * 企业法人
     */
    private String companyLegal;

    /**
     * 企业邮件
     */
    private String companyEmail;

    /**
     * 邮政编码
     */
    private String companyPostcode;

    /**
     * 状态 0启用 1禁用
     */
    private Integer companyStatus;

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
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyAbbreviation() {
        return companyAbbreviation;
    }

    public void setCompanyAbbreviation(String companyAbbreviation) {
        this.companyAbbreviation = companyAbbreviation;
    }
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyTax() {
        return companyTax;
    }

    public void setCompanyTax(String companyTax) {
        this.companyTax = companyTax;
    }
    public String getAreaProvince() {
        return areaProvince;
    }

    public void setAreaProvince(String areaProvince) {
        this.areaProvince = areaProvince;
    }
    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }
    public String getAreaCounty() {
        return areaCounty;
    }

    public void setAreaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }
    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }
    public String getCompanyLegal() {
        return companyLegal;
    }

    public void setCompanyLegal(String companyLegal) {
        this.companyLegal = companyLegal;
    }
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    public String getCompanyPostcode() {
        return companyPostcode;
    }

    public void setCompanyPostcode(String companyPostcode) {
        this.companyPostcode = companyPostcode;
    }
    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
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
        return "BasicCompany{" +
            "id=" + id +
            ", companyCode=" + companyCode +
            ", companyName=" + companyName +
            ", companyAbbreviation=" + companyAbbreviation +
            ", companyType=" + companyType +
            ", companyTax=" + companyTax +
            ", areaProvince=" + areaProvince +
            ", areaCity=" + areaCity +
            ", areaCounty=" + areaCounty +
            ", companyAddress=" + companyAddress +
            ", companyPhone=" + companyPhone +
            ", companyFax=" + companyFax +
            ", companyLegal=" + companyLegal +
            ", companyEmail=" + companyEmail +
            ", companyPostcode=" + companyPostcode +
            ", companyStatus=" + companyStatus +
            ", isDel=" + isDel +
            ", createTime=" + createTime +
            ", lastUpdateTime=" + lastUpdateTime +
            ", remark=" + remark +
        "}";
    }
}
