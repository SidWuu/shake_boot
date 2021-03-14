package com.sid.xk.shake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.**.controller", "com.**.service", "com.**.dao", "com.**.mapper"})
public class ShakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakeApplication.class, args);
	}

}
