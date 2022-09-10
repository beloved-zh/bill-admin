package com.beloved.system.service.impl;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.entity.SysMenu;
import com.beloved.system.mapper.SysMenuMapper;
import com.beloved.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;
    
    @Autowired
    private MenuConverter menuConverter;

    @Override
    public List<MenuDto> queryMenuTreeByUserId(Long userId) {
        
        List<MenuDto> userMenuList = menuConverter.toArrayDto(menuMapper.queryMenuListByUserId(userId));
        
        return this.getTopLevelMenuTree(userMenuList);
    }

    private List<MenuDto> getTopLevelMenuTree(List<MenuDto> menuList) {
        
        List<MenuDto> menuTree = menuList.stream()
                .filter(menu -> menu.getParentId() == 0)
                .peek(menu -> menu.setChildren(getMenuTree(menuList, menu)))
                .sorted(Comparator.comparing(MenuDto::getOrderNum))
                .collect(Collectors.toList());

        return menuTree;
    }

    private List<MenuDto> getMenuTree(List<MenuDto> menuList, MenuDto parentMenu) {

        List<MenuDto> children = menuList.stream()
                .filter(item -> parentMenu.getMenuId().equals(item.getParentId()))
                .peek(menu -> menu.setChildren(getMenuTree(menuList, menu)))
                .sorted(Comparator.comparing(MenuDto::getOrderNum))
                .collect(Collectors.toList());

        return children;
    }

    @Override
    public List<MenuDto> queryMenuList(SysMenu menu) {
        return menuConverter.toArrayDto(menuMapper.queryMenuList(menu));
    }

    @Override
    public List<MenuDto> queryMenuTree(SysMenu menu) {
        List<MenuDto> menuDtoList = menuConverter.toArrayDto(menuMapper.queryMenuList(menu));

        return this.getTopLevelMenuTree(menuDtoList);
    }
}
