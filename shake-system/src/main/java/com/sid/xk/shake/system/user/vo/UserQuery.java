package com.sid.xk.shake.system.user.vo;

import com.sid.xk.shake.vo.QueryBean;

import java.time.LocalDateTime;

/**
 * @author wuxiaodong
 * @date 2021/04/18
 */
public class UserQuery extends QueryBean {

    /** 用户名 */
    private String userName;
    /** 真实姓名 */
    private String realName;
    /** 头像 */
    private String avatar;
    /** 生日 */
    private LocalDateTime birthday;
    /** 性别 0未知 1男 2女 */
    private Integer sex;
    /** 昵称 */
    private String userNick;
    /** 用户类型 0超管 1管理员 2普通用户 */
    private Integer userType;
    /** 电话号码 */
    private String userPhone;
    /** QQ */
    private String userQq;

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

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
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
}
