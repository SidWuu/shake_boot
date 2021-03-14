package com.sid.xk.shake.common.enums;

public enum ReturnState {

    /** 成功 */
    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败")
    ;

    /** 返回状态 */
    private Integer state;
    /** 返回信息 */
    private String msg;

    public ReturnState state(String name) {
        return ReturnState.valueOf(name);
    }

    public ReturnState state(String name, String msg) {
        ReturnState s = ReturnState.valueOf(name);
        s.msg = msg;
        return s;
    }

    ReturnState(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
