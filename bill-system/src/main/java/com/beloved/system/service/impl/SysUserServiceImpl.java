package com.beloved.system.service.impl;

import com.beloved.common.entity.SysUser;
import com.beloved.system.mapper.SysUserMapper;
import com.beloved.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList() {
        return userMapper.selectUserList();
    }

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public SysUser queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }
}
