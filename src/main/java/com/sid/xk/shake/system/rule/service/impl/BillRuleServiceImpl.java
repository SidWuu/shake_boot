package com.sid.xk.shake.system.rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.system.rule.entity.SystemBillRule;
import com.sid.xk.shake.system.rule.mapper.BillRuleMapper;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 单据代码规则表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-04
 */
@Service
public class BillRuleServiceImpl extends ServiceImpl<BillRuleMapper, SystemBillRule> implements IBillRuleService {

    @Override
    public SystemBillRule getRule(String table, String column) {
        Map<String, String> params = new HashMap<>();
        params.put("table_name", table);
        params.put("code_column", column);
        return baseMapper.selectOne(new QueryWrapper<SystemBillRule>().allEq(params));
    }


}
