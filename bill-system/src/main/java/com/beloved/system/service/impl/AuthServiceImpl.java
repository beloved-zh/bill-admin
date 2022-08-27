package com.beloved.system.service.impl;

import com.beloved.common.model.dto.MenuDto;
import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.system.service.AuthService;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:27
 * @Description: 认证授权相关业务
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private SysMenuService menuService;
    
    @Override
    public UserInfoDto getUserInfo() {
        return SecurityUtils.getLoginUser().getUser();
    }

    @Override
    public List<MenuDto> queryMenuTree() {
        Long userId = SecurityUtils.getUserId();
        return menuService.queryMenuTreeByUserId(userId);
    }
}
