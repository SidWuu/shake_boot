package com.sid.xk.shake.basic.company.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 企业联系人表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-02
 */
public class BasicCompanyLinkman implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 联系人代码
     */
    private String linkmanCode;

    /**
     * 企业代码
     */
    private String companyCode;

    /**
     * 姓名
     */
    private String linkmanName;

    /**
     * 电话
     */
    private String linkmanPhone;

    /**
     * 其他电话
     */
    private String linkmanPhoneOther;

    /**
     * 微信
     */
    private String linkmanWechat;

    /**
     * QQ
     */
    private String linkmanQq;

    /**
     * 传真
     */
    private String linkmanFax;

    /**
     * 电子邮箱
     */
    private String linkmanEmail;

    /**
     * 地址
     */
    private String linkmanAddress;

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

    public String getLinkmanCode() {
        return linkmanCode;
    }

    public void setLinkmanCode(String linkmanCode) {
        this.linkmanCode = linkmanCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanPhoneOther() {
        return linkmanPhoneOther;
    }

    public void setLinkmanPhoneOther(String linkmanPhoneOther) {
        this.linkmanPhoneOther = linkmanPhoneOther;
    }

    public String getLinkmanWechat() {
        return linkmanWechat;
    }

    public void setLinkmanWechat(String linkmanWechat) {
        this.linkmanWechat = linkmanWechat;
    }

    public String getLinkmanQq() {
        return linkmanQq;
    }

    public void setLinkmanQq(String linkmanQq) {
        this.linkmanQq = linkmanQq;
    }

    public String getLinkmanFax() {
        return linkmanFax;
    }

    public void setLinkmanFax(String linkmanFax) {
        this.linkmanFax = linkmanFax;
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

    public String getLinkmanAddress() {
        return linkmanAddress;
    }

    public void setLinkmanAddress(String linkmanAddress) {
        this.linkmanAddress = linkmanAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BasicCompanyLinkman{" +
            "id=" + id +
            ", linkmanCode=" + linkmanCode +
            ", companyCode=" + companyCode +
            ", linkmanName=" + linkmanName +
            ", linkmanPhone=" + linkmanPhone +
            ", linkmanPhoneOther=" + linkmanPhoneOther +
            ", linkmanWechat=" + linkmanWechat +
            ", linkmanQq=" + linkmanQq +
            ", linkmanFax=" + linkmanFax +
            ", linkmanEmail=" + linkmanEmail +
            ", linkmanAddress=" + linkmanAddress +
            ", remark=" + remark +
        "}";
    }
}
