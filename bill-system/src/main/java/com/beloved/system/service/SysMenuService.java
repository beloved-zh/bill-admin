package com.beloved.system.service;

import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.entity.system.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
public interface SysMenuService {

    List<MenuDto> queryMenuTreeByUserId(Long userId);
    
    List<MenuDto> queryMenuList(SysMenu menu);

    List<MenuDto> queryMenuTree(SysMenu menu);
    
    long saveMenu(SysMenu menu);

    long editMenu(SysMenu menu);

    void removeMenu(Long menuId);
}
