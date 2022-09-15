package com.beloved.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-14 15:06
 * @Description: Jackson工具类
 */
@Slf4j
@Component
public class JsonUtils {

    @Autowired
    private ObjectMapper objectMapper;
    
    public String toJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ObjectNode parseObject(String text) {
        try {
            return objectMapper.readValue(text, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public <T> T parseObject(String text, Class<T> clazz) {
        try {
            return objectMapper.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(Reader input, Class<T> clazz) {
        try {
            return objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(URL url, Class<T> clazz) {
        try {
            return objectMapper.readValue(url, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(InputStream inputStream, Class<T> clazz) {
        try {
            return objectMapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayNode parseArray(String text) {
        try {
            return objectMapper.readValue(text, ArrayNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public <T> List<T> parseArray(String text, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(text, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
