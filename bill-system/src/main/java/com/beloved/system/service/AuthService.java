package com.beloved.system.service;

import com.beloved.common.model.dto.UserInfoDto;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:27
 * @Description: 认证授权相关业务
 */
public interface AuthService {
    
    UserInfoDto getUserInfo();
    
}
