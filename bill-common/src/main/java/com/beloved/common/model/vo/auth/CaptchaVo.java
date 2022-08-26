package com.beloved.common.model.vo.auth;

import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-28 09:36
 * @Description:
 */
@Data
public class CaptchaVo {
    
    private Boolean onOff;
    
    private String uuid;
    
    private String image;
    
}
