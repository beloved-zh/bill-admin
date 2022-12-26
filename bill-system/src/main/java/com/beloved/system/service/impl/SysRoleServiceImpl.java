package com.beloved.system.service.impl;

import com.beloved.common.model.dto.system.RoleDto;
import com.beloved.common.model.entity.system.SysRole;
import com.beloved.system.mapper.SysRoleMapper;
import com.beloved.system.mapper.SysRoleMenuMapper;
import com.beloved.system.service.SysRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRole> queryList(SysRole role) {
        return sysRoleMapper.queryList(role);
    }

    @Override
    public long saveRole(SysRole role) {
        sysRoleMapper.saveRole(role);
        return role.getRoleId();
    }

    @Override
    public long editRole(SysRole role) {
        sysRoleMapper.editRole(role);
        return role.getRoleId();
    }

    @Override
    public List<Long> getRoleAuthMenus(long roleId) {
        return sysRoleMenuMapper.getRoleAuthMenus(roleId);
    }

    @Transactional
    @Override
    public void authRoleMenus(RoleDto roleDto) {
        sysRoleMenuMapper.removeByRoleId(roleDto.getRoleId());
        
        if (CollectionUtils.isEmpty(roleDto.getMenus())) {
            return;
        }
        
        sysRoleMenuMapper.batchSaveByRoleId(roleDto);
    }

    @Transactional
    @Override
    public void removeRole(long roleId) {
        sysRoleMapper.removeByRoleId(roleId);
        sysRoleMenuMapper.removeByRoleId(roleId);
    }
}
