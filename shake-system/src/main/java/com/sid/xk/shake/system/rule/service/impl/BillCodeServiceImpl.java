package com.sid.xk.shake.system.rule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.component.RedisComp;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.BillEnum;
import com.sid.xk.shake.exception.BaseException;
import com.sid.xk.shake.system.rule.entity.SystemBillCode;
import com.sid.xk.shake.system.rule.entity.SystemBillRule;
import com.sid.xk.shake.system.rule.mapper.BillCodeMapper;
import com.sid.xk.shake.system.rule.service.IBillCodeService;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import com.sid.xk.shake.utils.DateUtil;
import com.sid.xk.shake.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 单据最大代码表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-04
 */
@Service
public class BillCodeServiceImpl extends ServiceImpl<BillCodeMapper, SystemBillCode> implements IBillCodeService {

    @Autowired
    private IBillRuleService billRuleService;
    @Resource
    private RedisComp redisComp;


    @Override
    public String getMaxCode(BillEnum billEnum) {
        Objects.requireNonNull(billEnum, "参数为空");
        String table = billEnum.getTable(), column = billEnum.getColumn();
        SystemBillRule rule = billRuleService.lambdaQuery().eq(SystemBillRule::getTableName, table).eq(SystemBillRule::getCodeColumn, column).one();
        if (null == rule) {
            BaseException.throwException(String.format("[%s/%s]未设置单据代码生成规则", table, column));
        }
        String code = StringUtil.isEmpty(rule.getCodePrefix()) ? "" : rule.getCodePrefix();
        LocalDateTime time = null;
        if (BaseConstants.STATUS_0.equals(rule.getIsDate())) {
            time = LocalDateTime.now();
            code += DateUtil.getDateTimeStr(time, rule.getDateFormat());
        }
        String nextCode = getNextCode(rule, time);
        int fillLength = rule.getCodeLength() - code.length();
        if (fillLength < 0) {
            BaseException.throwException("单号超过设定长度");
        }
        code += StringUtil.fillLeft(nextCode, '0', fillLength);
        return code;
    }

    private String getNextCode(SystemBillRule rule, LocalDateTime time) {
        String key = String.format("CODE-%s-%s", rule.getTableName(), rule.getCodeColumn());
        // redis 过期或不存在
        Object value = redisComp.get(key);
        if (null == value) {
            // 从数据库查询最大一条
            SystemBillCode billCode = lambdaQuery().orderByDesc(SystemBillCode::getDateTime).last("limit 1").one();
            if (null != billCode) {
                redisComp.set(key, billCode.getMaxCode());
            }
        }
        long maxCode = redisComp.getMaxCode(key);
        // 更新单号表数据
        SystemBillCode saveMod = new SystemBillCode(rule.getTableName(), rule.getCodeColumn(), rule.getDateFormat(), time, maxCode);
        boolean success = true;
        try {
            success = saveOrUpdate(saveMod, lambdaQuery().eq(SystemBillCode::getTableName, rule.getTableName()).eq(SystemBillCode::getCodeColumn, rule.getCodeColumn()));
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("同步单号失败");
        }
        redisComp.set(key, maxCode);
        return String.valueOf(maxCode);
    }

}
