package com.xzm.wisdomwaterauth.service;

import com.alibaba.fastjson.JSON;
import com.xzm.wisdomwaterauth.entity.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${token.access-expire-second}")
    private Long accessExpire;

    private static final String TOKEN_KEY_PREFIX = "oauth:access_token:";

    /**
     * 创建accessToken，存入Redis
     */
    public String createToken(CustomerUserDetails user) {
        // 生成随机token字符串
        String accessToken = UUID.randomUUID().toString().replace("-", "");
        String redisKey = TOKEN_KEY_PREFIX + accessToken;
        // value存放用户json
        String userJson = JSON.toJSONString(user);
        redisTemplate.opsForValue().set(redisKey, userJson, accessExpire, TimeUnit.SECONDS);
        return accessToken;
    }
}
