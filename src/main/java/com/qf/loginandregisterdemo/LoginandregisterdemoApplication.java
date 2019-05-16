package com.qf.loginandregisterdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.qf.dao")
@SpringBootApplication(scanBasePackages = "com.qf")
@EnableTransactionManagement
public class LoginandregisterdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginandregisterdemoApplication.class, args);
    }

}
