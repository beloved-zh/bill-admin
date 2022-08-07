package com.beloved.system.service;

import com.beloved.common.model.entity.SysMenu;

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
    
    List<SysMenu> queryMenuList(SysMenu menu);
    
}
