package com.beloved.system.service.impl;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.dto.system.UserInfoDto;
import com.beloved.common.utils.SecurityUtils;
import com.beloved.system.mapper.SysMenuMapper;
import com.beloved.system.service.AuthService;
import com.beloved.system.service.SysMenuService;
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
    
    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private MenuConverter menuConverter;
    
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
