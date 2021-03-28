package com.sid.xk.shake.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-27
 */
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String userNick;

    /**
     * 密码(加密后)
     */
    private String userPassword;

    /**
     * 用户类型 0超管 1管理员 2普通用户
     */
    private Integer userType;

    /**
     * 状态 0启用 1禁用
     */
    private Integer userStatus;

    /**
     * 电话号码
     */
    private String userPhone;

    /**
     * QQ
     */
    private String userQq;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    @Override
    public String toString() {
        return "SystemUser{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userNick=" + userNick +
            ", userPassword=" + userPassword +
            ", userType=" + userType +
            ", userStatus=" + userStatus +
            ", userPhone=" + userPhone +
            ", userQq=" + userQq +
        "}";
    }
}
