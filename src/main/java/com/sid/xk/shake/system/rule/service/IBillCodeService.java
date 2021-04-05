package com.sid.xk.shake.system.rule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.system.rule.entity.SystemBillCode;

/**
 * <p>
 * 单据最大代码表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-04
 */
public interface IBillCodeService extends IService<SystemBillCode> {


    /**
     * 获取最大单号
     * @param table
     * @param column
     * @return
     */
    String getMaxCode(String table, String column);


}
