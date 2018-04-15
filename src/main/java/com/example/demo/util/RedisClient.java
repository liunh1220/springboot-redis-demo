package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by zhoufurong on 2017/11/1.
 */

/**
 * Redis客户端
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean isExists(String key){

       return (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
               return  redisConnection.exists(key.getBytes());
            }
        });

    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void set(String key, String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }
    public void set(String key, final String value,final long expireTime) {
       redisTemplate.execute((RedisCallback) redisConnection -> {
           redisConnection.set(key.getBytes(),value.getBytes());
           return null;
       });

    }


    public Optional<String> get( String key) {
        String result = (String) redisTemplate.execute((RedisCallback) redisConnection -> {
            String result1 = null;
            try {
                result1 = new String(redisConnection.get(key.getBytes()));
            }catch (Exception e){
                return null;
            }
            return result1;

        });
        return result == null ? Optional.empty() : Optional.of(result.toString());
    }

    public void hashSet(String key, String field, String value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);

    }


    public Optional<String> hashGet(String key, String field) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object result = hashOperations.get(key, field);
        return result == null ? Optional.empty() : Optional.of(result.toString());
    }




}
