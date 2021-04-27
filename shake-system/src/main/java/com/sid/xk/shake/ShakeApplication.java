package com.sid.xk.shake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @author wuxiaodong
 * @date 2021/04/10
 */
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.sid.xk.shake.**.controller", "com.sid.xk.shake.**.service", "com.sid.xk.shake.**.component"})
@MapperScan(basePackages = "com.sid.xk.shake.**.mapper")
public class ShakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShakeApplication.class, args);
    }

}
