package com.beloved.system.service.impl;

import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.common.model.entity.SysUser;
import com.beloved.system.service.AuthService;
import com.beloved.system.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:27
 * @Description: 认证授权相关业务
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Override
    public UserInfoDto getUserInfo() {
        
        SysUser user = SecurityUtils.getLoginUser().getUser();

        return new UserInfoDto(user);
    }
}
