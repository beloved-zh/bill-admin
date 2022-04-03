package com.beloved.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * Redis 核心配置
 *
 * @author beloved
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // JSON 序列化配置
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        /**
         * json序列化时，不仅是根据get方法来序列化的，而是实体类中所有的有返回值的方法都会将返回的值序列化，
         * 但是反序列化时是根据set方法来实现的，所以当实体类中有非get，set方法的方法有返回值时，反序列化时就会出错。
         * Could not read JSON: Unrecognized field “enabled”
         *
         * 例如：LoginUser实现UserDetails 中有非属性的get、set方法，反序列化是会报错
         */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsonRedisSerializer.setObjectMapper(objectMapper);

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
