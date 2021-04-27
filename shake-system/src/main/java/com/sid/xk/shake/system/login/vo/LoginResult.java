package com.sid.xk.shake.system.login.vo;

import com.sid.xk.shake.basic.dictionary.entity.BasicDictionary;
import com.sid.xk.shake.system.user.entity.SystemUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 登录返回信息
 * @author wuxiaodong
 * @date 2021/04/25
 */
public class LoginResult implements Serializable {
    /** Token */
    private String token;
    /** 用户信息 */
    private SystemUser user;
    /** 登录时间戳 */
    private Long timestamp;
    /** 菜单权限, 已设计未开发 */
    private Map<String, List<Object>> permission;
    /** 数据字段 */
    private Map<String, List<BasicDictionary>> dictionary;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, List<Object>> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, List<Object>> permission) {
        this.permission = permission;
    }

    public Map<String, List<BasicDictionary>> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, List<BasicDictionary>> dictionary) {
        this.dictionary = dictionary;
    }
}
