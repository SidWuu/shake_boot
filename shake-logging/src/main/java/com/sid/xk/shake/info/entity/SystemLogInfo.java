package com.sid.xk.shake.info.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
public class SystemLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 功能模块
     */
    private String module;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作描述
     */
    private String remark;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回参数
     */
    private String responseParam;

    /**
     * 操作员用户名
     */
    private String userName;

    /**
     * 操作员昵称
     */
    private String userNick;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 请求URI
     */
    private String uri;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
    public String getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(String responseParam) {
        this.responseParam = responseParam;
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
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SystemLogInfo{" +
            "id=" + id +
            ", module=" + module +
            ", type=" + type +
            ", remark=" + remark +
            ", requestParam=" + requestParam +
            ", responseParam=" + responseParam +
            ", userName=" + userName +
            ", userNick=" + userNick +
            ", method=" + method +
            ", uri=" + uri +
            ", ip=" + ip +
            ", createTime=" + createTime +
        "}";
    }
}
