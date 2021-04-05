package com.sid.xk.shake;

import com.sid.xk.shake.system.rule.entity.SystemBillRule;
import com.sid.xk.shake.system.rule.service.IBillRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShakeApplication.class)
public class ShakeApplicationTests {

	@Autowired
	private IBillRuleService billRuleService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void saveBillRule() {
//		SystemBillRule rule = new SystemBillRule();
//		rule.setTableName("basic_company");
//		rule.setCodeColumn("company_code");
//		rule.setCodeLength(10);
//		rule.setIsDate(1);
//		billRuleService.save(rule);
		// 基础设置代码规则生成
		Map<String, String> rules = new HashMap<>() {{
			put("basic_company_linkman", "linkman_code");
			put("basic_product", "product_code");
			put("basic_warearea", "warearea_code");
			put("basic_warehouse", "warehouse_code");
			put("system_menu", "menu_code");
		}};

		for (String key : rules.keySet()) {
			SystemBillRule rule = new SystemBillRule();
			rule.setTableName(key);
			rule.setCodeColumn(rules.get(key));
			rule.setCodeLength(10);
			rule.setIsDate(1);
			billRuleService.save(rule);
		}

	}

}
