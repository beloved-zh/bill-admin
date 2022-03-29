package com.beloved.system.mapper;


import com.beloved.common.entity.SysUser;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserMapper {

    /**
     * 根据条件分页查询用户列表
     * 
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList();

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser queryUserByUserName(String userName);
}
