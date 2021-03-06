package com.sid.xk.shake.constants;

/**
 * 常量
 * @author wuxiaodong
 * @date 2021/04/03
 */
public final class BaseConstants {
    /** 返回状态 */
    public static final String RES_RETURN_STATUS = "status";
    /** 返回信息 */
    public static final String RES_RETURN_MESSAGE = "message";
    /** 返回数据 */
    public static final String RES_RETURN_DATA = "data";

    /** 公用拆分字符 */
    public static final String SPLIT_CHARACTER = ",";

    /** 操作标志 0新增 */
    public static final Integer DATA_FLAG_0 = 0;
    /** 操作标志 1修改 */
    public static final Integer DATA_FLAG_1 = 1;
    /** 操作标志 2删除 */
    public static final Integer DATA_FLAG_2 = 2;

    /** 公用状态0 */
    public static final Integer STATUS_0 = 0;
    /** 公用状态1 */
    public static final Integer STATUS_1 = 1;
    /** 公用状态2 */
    public static final Integer STATUS_2 = 2;

    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";
    /** Request 消息头 token */
    public final static String X_ACCESS_TOKEN = "X-Access-Token";

    private BaseConstants() {
    }
}
