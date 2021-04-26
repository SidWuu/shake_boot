package com.sid.xk.shake.system.rule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.common.constants.BillEnum;
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
     * @param billEnum 单据枚举
     * @return String
     */
    String getMaxCode(BillEnum billEnum);


}
