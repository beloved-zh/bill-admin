package com.beloved.system.mapper;

import com.beloved.common.model.dto.system.RoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    void removeByMenuId(long menuId);

    List<Long> getRoleAuthMenus(long roleId);

    void removeByRoleId(long roleId);
    
    void batchSaveByRoleId(RoleDto roleDto);
}
