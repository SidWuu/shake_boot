package com.sid.xk.shake.system.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String userNick;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 性别 0未知 1男 2女
     */
    private Integer sex;

    /**
     * 密码(加密后)
     */
    private String userPassword;

    /**
     * 盐(MD5)
     */
    private String salt;

    /**
     * 用户类型 0超管 1管理员 2普通用户
     */
    private Integer userType;

    /**
     * 电话号码
     */
    private String userPhone;

    /**
     * QQ
     */
    private String userQq;

    /**
     * 状态 0启用 1禁用
     */
    private Integer userStatus;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", userName=" + userName +
                ", realName=" + realName +
                ", userNick=" + userNick +
                ", avatar=" + avatar +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", userPassword=" + userPassword +
                ", salt=" + salt +
                ", userType=" + userType +
                ", userPhone=" + userPhone +
                ", userQq=" + userQq +
                ", userStatus=" + userStatus +
                ", isDel=" + isDel +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                "}";
    }
}
