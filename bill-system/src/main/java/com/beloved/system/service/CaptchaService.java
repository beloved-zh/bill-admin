package com.beloved.system.service;

import com.beloved.common.model.vo.system.CaptchaVo;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-28 09:45
 * @Description: 验证码
 */
public interface CaptchaService {

    CaptchaVo createCaptcha();
    
    void verify(String uuid, String code);
}
