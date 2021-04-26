package com.sid.xk.shake.constants;

/**
 *
 * @author wuxiaodong
 * @date 2021/04/03
 */
public enum StatusEnum {

    /** 成功 */
    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败")
    ;

    /** 返回状态 */
    private Integer status;
    /** 返回信息 */
    private String msg;

    StatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
