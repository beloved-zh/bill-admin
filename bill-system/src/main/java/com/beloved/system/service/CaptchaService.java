package com.beloved.system.service;

import com.beloved.common.model.dto.CaptchaDto;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-28 09:45
 * @Description: 验证码
 */
public interface CaptchaService {

    CaptchaDto createCaptcha();
    
    void verify(String uuid, String code);
}
