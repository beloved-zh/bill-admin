package com.beloved.system.service.impl;

import com.beloved.common.constant.MenuConstants;
import com.beloved.common.converter.MenuConverter;
import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.model.dto.MenuDto;
import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.common.utils.StringUtils;
import com.beloved.system.mapper.SysMenuMapper;
import com.beloved.system.service.AuthService;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<MenuDto> userMenuList = menuConverter.toArrayDto(menuMapper.queryMenuListByUserId(userId));

        return this.getTopLevelMenuTree(userMenuList, MenuConstants.LAYOUT);
    }

    /**
     * 获取顶层菜单树
     * @param menuList      菜单集合
     * @param defaultCom    顶层菜单默认组件
     * @return
     */
    private List<MenuDto> getTopLevelMenuTree(List<MenuDto> menuList, String defaultCom) {

        List<MenuDto> menuTree = menuList.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu -> {
                    menu.setChildren(getMenuTree(menuList, menu, MenuConstants.PARENT_VIEW));
                    if (MenuTypeEnum.DIR.equals(menu.getMenuType()) && StringUtils.isEmpty(menu.getComponent())) {
                        menu.setComponent(defaultCom);
                    }
                    return menu;
                })
                .sorted(Comparator.comparing(MenuDto::getOrderNum))
                .collect(Collectors.toList());

        return menuTree;
    }

    /**
     * 获取单树
     * @param menuList      菜单集合
     * @param defaultCom    菜单默认组件
     * @return
     */
    private List<MenuDto> getMenuTree(List<MenuDto> menuList, MenuDto parentMenu, String defaultCom) {

        List<MenuDto> children = menuList.stream()
                .filter(item -> parentMenu.getMenuId().equals(item.getParentId()))
                .map(menu -> {
                    menu.setChildren(getMenuTree(menuList, menu, defaultCom));
                    if (MenuTypeEnum.DIR.equals(menu.getMenuType()) && StringUtils.isEmpty(menu.getComponent())) {
                        menu.setComponent(defaultCom);
                    }
                    return menu;
                })
                .sorted(Comparator.comparing(MenuDto::getOrderNum))
                .collect(Collectors.toList());

        return children;
    }
}
