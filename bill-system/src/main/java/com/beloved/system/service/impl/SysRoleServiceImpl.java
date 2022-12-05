package com.beloved.system.service.impl;

import com.beloved.common.model.entity.system.SysRole;
import com.beloved.system.mapper.SysRoleMapper;
import com.beloved.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SysRole> queryList(SysRole role) {
        return sysRoleMapper.queryList(role);
    }
}
