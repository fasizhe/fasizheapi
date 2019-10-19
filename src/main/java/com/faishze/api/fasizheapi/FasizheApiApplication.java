package com.faishze.api.fasizheapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com")
@RestController
public class FasizheApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FasizheApiApplication.class, args);
    }
}
