package com.beloved.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beloved.common.model.entity.SysUserRole;
import com.beloved.system.mapper.SysUserRoleMapper;
import com.beloved.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
