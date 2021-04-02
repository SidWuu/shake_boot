package com.sid.xk.shake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.sid.xk.shake.basic.company.controller", "com.sid.xk.shake.basic.company.service"})
@MapperScan(basePackages = "com.sid.xk.shake.**.mapper")
public class ShakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakeApplication.class, args);
	}

}
