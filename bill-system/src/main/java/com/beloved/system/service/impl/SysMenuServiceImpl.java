package com.beloved.system.service.impl;

import com.beloved.common.model.entity.SysMenu;
import com.beloved.system.mapper.SysMenuMapper;
import com.beloved.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    
    @Override
    public List<SysMenu> queryMenuList(SysMenu menu) {
        return menuMapper.queryMenuList(menu);
    }
}
