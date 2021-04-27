package com.sid.xk.shake.system.rule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.system.rule.entity.SystemBillRule;
import com.sid.xk.shake.system.rule.mapper.BillRuleMapper;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import org.springframework.stereotype.Service;

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
}
