package com.beloved.system.security.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-29 11:26
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "token")
public class TokenConfig {

    private static final String DEFAULT_HEADER = "Authorization";
    private static final String DEFAULT_TOKEN_PREFIX = "Bearer";
    private static final Long DEFAULT_EXPIRE_TIME = 30L;
    
    private String header = DEFAULT_HEADER;
    private String tokenPrefix = DEFAULT_TOKEN_PREFIX;
    private Long expireTime = DEFAULT_EXPIRE_TIME;
    private String secret;
    
}
