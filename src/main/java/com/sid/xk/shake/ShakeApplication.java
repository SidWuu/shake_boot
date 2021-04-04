package com.sid.xk.shake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.sid.xk.shake.**.controller", "com.sid.xk.shake.system.rule.service", "com.sid.xk.shake.common.utils"})
@MapperScan(basePackages = "com.sid.xk.shake.**.mapper")
public class ShakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakeApplication.class, args);
	}

}
