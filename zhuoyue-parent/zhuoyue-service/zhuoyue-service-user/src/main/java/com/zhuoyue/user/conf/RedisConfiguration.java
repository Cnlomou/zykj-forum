package com.zhuoyue.user.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuoyue.user.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @author Linmo
 * @create 2020/4/24 14:01
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisCacheJwtManager<User> redisCacheJwtManager(RedisTemplate redisTemplate) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(new ObjectMapper());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        return new RedisCacheJwtManager<>(redisTemplate);
    }
}
