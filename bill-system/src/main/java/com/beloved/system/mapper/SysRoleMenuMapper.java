package com.beloved.system.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Mapper
public interface SysRoleMenuMapper {

    void removeByMenuId(Long menuId);
    
}
