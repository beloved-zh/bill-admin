package com.beloved.system.service.impl;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.entity.system.SysMenu;
import com.beloved.system.mapper.SysMenuMapper;
import com.beloved.system.mapper.SysRoleMenuMapper;
import com.beloved.system.service.SysMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    private SysRoleMenuMapper roleMenuMapper;
    
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

    @Override
    public Long saveMenu(SysMenu menu) {
        
        if (menu.getParentId() == 0 && Objects.equals(menu.getMenuType(), MenuTypeEnum.BUTTON)) {
            throw new ServiceException("主菜单不能添加按钮");    
        }

        if (menu.getParentId() > 0) {
            SysMenu parentMenu = menuMapper.queryMenuById(menu.getParentId());
            if (Objects.equals(parentMenu.getMenuType(), MenuTypeEnum.DIR) && Objects.equals(menu.getMenuType(), MenuTypeEnum.BUTTON)) {
                throw new ServiceException("目录下不能添加按钮");
            }
            if (Objects.equals(parentMenu.getMenuType(), MenuTypeEnum.MENU) && Objects.equals(menu.getMenuType(), MenuTypeEnum.DIR)) {
                throw new ServiceException("菜单下不能添加目录");
            }
            if (Objects.equals(parentMenu.getMenuType(), MenuTypeEnum.BUTTON)) {
                throw new ServiceException("按钮下不能添加子元素");
            }
        }
        
        menuMapper.saveMenu(menu);
        
        return menu.getMenuId();
    }

    @Override
    public Long editMenu(SysMenu menu) {
        
        menuMapper.editMenu(menu);

        return menu.getMenuId();
    }

    @Override
    public void removeMenu(Long menuId) {

        List<SysMenu> childrenMenu = menuMapper.queryChildrenMenuByParentId(menuId);

        if (CollectionUtils.isNotEmpty(childrenMenu)) {
            throw new ServiceException("存在子菜单，不允许删除");
        }

        roleMenuMapper.removeByMenuId(menuId);
        
        menuMapper.removeMenu(menuId);
    }
}
