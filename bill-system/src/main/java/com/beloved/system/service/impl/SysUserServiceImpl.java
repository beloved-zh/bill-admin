package com.beloved.system.service.impl;

import com.beloved.common.model.dto.system.UserInfoDto;
import com.beloved.common.model.entity.system.SysUser;
import com.beloved.system.mapper.SysUserMapper;
import com.beloved.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public UserInfoDto queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }
}
