package com.faishze.api.fasizheapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.faishze.api.fasizheapi.dao"})
@SpringBootApplication(scanBasePackages = "com")
public class FasizheApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FasizheApiApplication.class, args);
    }

}
