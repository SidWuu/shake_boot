package com.sid.xk.shake.system.log.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 异常日志表
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
public class SystemLogError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 异常名称
     */
    private String exceptionName;

    /**
     * 异常信息
     */
    private String exceptionMessage;

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
    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
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
        return "SystemLogError{" +
            "id=" + id +
            ", requestParam=" + requestParam +
            ", exceptionName=" + exceptionName +
            ", exceptionMessage=" + exceptionMessage +
            ", userName=" + userName +
            ", userNick=" + userNick +
            ", method=" + method +
            ", uri=" + uri +
            ", ip=" + ip +
            ", createTime=" + createTime +
        "}";
    }
}
