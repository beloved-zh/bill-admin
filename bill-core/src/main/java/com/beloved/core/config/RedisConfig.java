package com.beloved.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 核心配置
 *
 * @author beloved
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private ObjectMapper redisObjectMapper;
    
    /**
     * RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // JSON 序列化配置
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jsonRedisSerializer.setObjectMapper(redisObjectMapper);

        // 设置key的序列化方式  String
        template.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式    JSON
        template.setValueSerializer(jsonRedisSerializer);
        // 设置hash的key的序列化方式 String
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置hash的value的序列化方式   JSON
        template.setHashValueSerializer(jsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

}
