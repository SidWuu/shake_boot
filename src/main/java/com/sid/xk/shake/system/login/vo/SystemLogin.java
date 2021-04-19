package com.sid.xk.shake.system.login.vo;

import java.io.Serializable;

/**
 * 登录表单
 * @author wuxiaodong
 * @date 2021/04/18
 */
public class SystemLogin implements Serializable {
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 验证码 */
    private String captcha;
    /** 验证码key */
    private String checkKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }
}
