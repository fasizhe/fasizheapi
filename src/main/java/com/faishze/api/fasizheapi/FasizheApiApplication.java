package com.faishze.api.fasizheapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = {"com.faishze.api.fasizheapi.dao"})
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "com")
public class FasizheApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FasizheApiApplication.class, args);
    }

}
