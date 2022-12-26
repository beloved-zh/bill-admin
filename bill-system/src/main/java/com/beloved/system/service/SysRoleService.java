package com.beloved.system.service;

import com.beloved.common.model.dto.system.RoleDto;
import com.beloved.common.model.entity.system.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
public interface SysRoleService {

    List<SysRole> queryList(SysRole role);
    
    long saveRole(SysRole role);

    long editRole(SysRole role);
    
    List<Long> getRoleAuthMenus(long roleId);

    void authRoleMenus(RoleDto roleDto);

    void removeRole(long roleId);
}
