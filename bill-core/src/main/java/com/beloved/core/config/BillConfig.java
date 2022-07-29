package com.beloved.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-27 16:38
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "bill")
public class BillConfig {

    private static final Boolean DEFAULT_CAPTCHA_ON_OFF = true;
    private static final String DEFAULT_CAPTCHA_TYPE = "gif";
    private static final Long DEFAULT_CAPTCHA_EXPIRATION = 2L;
    
    // 验证码开关
    private Boolean captchaOnOff = DEFAULT_CAPTCHA_ON_OFF;
    
    // 验证码类型
    private String captchaType = DEFAULT_CAPTCHA_TYPE;
    
    // 验证码过期时间 默认单位：分钟
    private Long captchaExpiration = DEFAULT_CAPTCHA_EXPIRATION;
}
