package com.faishze.api.fasizheapi.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * 描述: 简单封装 Properties 相关方法
 *
 * @author XHSF
 * @email 827032783@qq.com
 * @create 2019-08-08
 */
public final class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private PropertiesUtils() {}

    /**
     * 获取对应key的value
     * @param key 键
     * @param fileName properties文件名
     * @return 值
     */
    public static String getProperty(String key, String fileName) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Properties properties = new Properties();
        String value = null;
        try (InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8)) {
            properties.load(inputStreamReader);
            value = properties.getProperty(key.trim());
        } catch (IOException e) {
            logger.error("Get property exception.", e);
        }
        return value;
    }

    /**
     * 获取对应key的value，如果value为null，则返回defaultValue
     * @param key 键
     * @param defaultValue 默认值
     * @param fileName properties文件名
     * @return 值
     */
    public static String getProperty(String key, String defaultValue, String fileName){
        String value = getProperty(key, fileName);
        return StringUtils.isBlank(value) ? defaultValue : value;
    }

    /**
     * 获取对应key的value，如果value为null，则返回defaultValue
     * @param key 键
     * @param fileName properties文件名
     * @return 值
     */
    public static Integer getIntProperty(String key, String fileName){
        String value = getProperty(key, fileName);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * 获取对应key的value，如果value为null，则返回defaultValue
     * @param key 键
     * @param defaultValue 默认值
     * @param fileName properties文件名
     * @return 值
     */
    public static int getIntProperty(String key, int defaultValue, String fileName){
        Integer value = getIntProperty(key, fileName);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

}
