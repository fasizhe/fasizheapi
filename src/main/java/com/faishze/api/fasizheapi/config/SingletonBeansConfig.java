package com.faishze.api.fasizheapi.config;

import com.faishze.api.fasizheapi.util.ftp.FTPClientTemplate;
import com.google.gson.Gson;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 一些类库的单例
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-26
 */
@Configuration
public class SingletonBeansConfig {

    /**
     * Gson单例
     * @return Gson
     */
    @Bean
    public Gson gson() {
        return new Gson();
    }

    /**
     * RestTemplate单例
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        List<MediaType> mediaTypes = new ArrayList<>();
        //加入text/plain类型的支持
        mediaTypes.add(MediaType.TEXT_PLAIN);
        //加入text/html类型的支持
        mediaTypes.add(MediaType.TEXT_HTML);
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setSupportedMediaTypes(mediaTypes);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(messageConverter);
        return restTemplate;
    }

    /**
     * FTPClientTemplate单例
     * @return FTPClientTemplate
     */
    @Bean
    public FTPClientTemplate ftpClientTemplate(@Value("${ftp.host}") String host,
                                               @Value("${ftp.username}") String username,
                                               @Value("${ftp.password}") String password) {
        return new FTPClientTemplate(host, username, password);
    }


    /**
     * dozer配置
     *
     * @return Mapper
     */
    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }


}
