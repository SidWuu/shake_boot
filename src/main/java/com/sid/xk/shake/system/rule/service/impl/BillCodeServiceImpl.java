package com.sid.xk.shake.system.rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.common.component.RedisComp;
import com.sid.xk.shake.common.constants.BaseConstants;
import com.sid.xk.shake.common.exception.BaseException;
import com.sid.xk.shake.common.utils.DateUtil;
import com.sid.xk.shake.common.utils.StringUtil;
import com.sid.xk.shake.system.rule.entity.SystemBillCode;
import com.sid.xk.shake.system.rule.entity.SystemBillRule;
import com.sid.xk.shake.system.rule.mapper.BillCodeMapper;
import com.sid.xk.shake.system.rule.service.IBillCodeService;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
    public String getMaxCode(String table, String column) {
        if (StringUtil.isOneEmpty(table, column)) {
            BaseException.throwException("参数为空");
        }
        SystemBillRule rule = billRuleService.getRule(table, column);
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
            SystemBillCode billCode = baseMapper.selectOne(new QueryWrapper<SystemBillCode>().orderByDesc("date_time").last("limit 1"));
            if (null != billCode) {
                // 从数据库取
                redisComp.set(key, billCode.getMaxCode());
            }
        }
        long maxCode = redisComp.getMaxCode(key);
        // 更新单号表数据
        SystemBillCode saveMod = new SystemBillCode(rule.getTableName(), rule.getCodeColumn(), rule.getDateFormat(), time, maxCode);
        boolean success = true;
        try {
            success = saveOrUpdate(saveMod, new QueryWrapper<SystemBillCode>().eq("table_name", rule.getTableName()).eq("code_column", rule.getCodeColumn()));
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
