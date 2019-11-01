package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.dao.OauthMapper;
import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author masonluo
 * @date 2019/11/1 12:19 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FasizheApiApplication.class)
public class TestTypeHandler {
    @Autowired
    OauthMapper mapper;

    private String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

    @Test
    public void test(){
        Oauth query = mapper.selectByOauthIdAndOauthType("111", OauthType.WECHAT);
        System.out.println();
    }


}
