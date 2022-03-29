package com.beloved.system.service;

import com.beloved.common.entity.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> selectUserList();

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser queryUserByUserName(String userName);
}
