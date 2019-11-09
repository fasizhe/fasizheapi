package com.faishze.api.fasizheapi.util;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 杜科
 * @description 封装jedis操作的工具类，利用fastJson做中间序列化,默认每次命中缓存则重置过期时间为30分钟
 * @contact 15521177704
 * @since 2019/11/6
 */
public class JedisUtil {

    private final static Jedis jedis=new Jedis("47.98.242.142",6379);

    static {
        jedis.auth("Fasizhe2019");
    }

    //用redis的哈希结构缓存对象
    public static boolean hsetObject(String key,Object object){
        //反射设值
        Class c=object.getClass();
        for (Field declaredField : c.getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                String jsonString=JSON.toJSONString(declaredField.get(object));
                jedis.hset(key,declaredField.getName(), jsonString);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //设置为30分钟后过期
        jedis.expire(key,60*30);
        return true;
    }


    public static Object hgetObject(String key,Object object) {
        //Map map = jedis.hgetAll(key);
        //延长缓存时间，重置为30分钟
        jedis.expire(key,60*30);
        //反射设值
        Class c=object.getClass();
        for (Field declaredField : c.getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                String jsonString= jedis.hget(key,declaredField.getName());
                declaredField.set(object,JSON.parseObject(jsonString,declaredField.getType()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public static Object hgetField(String key, String field,Object value){
        //延长缓存时间，重置为30分钟
        jedis.expire(key,60*30);
        String jsonValue=jedis.hget(key,field);
        value=JSON.parseObject(jsonValue,value.getClass());
        return value;
    }

    public static boolean hsetField(String key,String field,Object value){
        String jsonValue=JSON.toJSONString(value);
        jedis.hset(key,field,jsonValue);
        //设置为30分钟后过期
        jedis.expire(key,60*30);
        return true;
    }

    public static boolean ishFieldExist(String key,String field){
        return jedis.hexists(key,field);
    }


    public static boolean isExist(String key){
        return jedis.exists(key);
    }

    public static boolean setObject(String key,Object object){
        String jsonString= JSON.toJSONString(object);
        jedis.set(key,jsonString);
        //设置为30分钟后过期
        jedis.expire(key,60*30);
        return true;
    }

    public static Object getObject(String key,Object object){
        //延长缓存时间，重置为30分钟
        jedis.expire(key,60*30);
        String jsonString=jedis.get(key);
        object=JSON.parseObject(jsonString,object.getClass());
        return object;
    }

    public static boolean deleteObject(String key){
        jedis.del(key);
        return true;
    }

    public static boolean hdeleteField(String key,String field){
        jedis.hdel(key,field);
        return true;
    }

    public static boolean setExpire(String key,int second){
        jedis.expire(key,second);
        return true;
    }

    public static boolean zsetMember(String key,double score,Object member){
        String jsonString=JSON.toJSONString(member);
        jedis.zadd(key,score,jsonString);
        //设置为30分钟后过期
        jedis.expire(key,60*30);
        return true;
    }

    public static List zgetMember(String key, int pageNum, int pageSize,Class c){
        //延长缓存时间，重置为30分钟
        jedis.expire(key,60*30);
        long start=(pageNum-1)*pageSize;
        long end=pageNum*pageSize-1;
        //降序
        Set<String> set=jedis.zrevrange(key,start,end);
        List list=new ArrayList();
        for (String s : set) {
            list.add(JSON.parseObject(s,c));
        }
        return list;
    }


}
