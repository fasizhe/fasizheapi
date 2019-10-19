package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.dao.UserMapper;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.validator.annotation.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com")
@RestController
public class FasizheApiApplication {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(FasizheApiApplication.class, args);
    }
    @RequestMapping("/")
    public List<User> index(){
        return userMapper.selectAll();
    }

}
