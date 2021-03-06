package com.beloved.system.service;

import com.beloved.common.model.entity.SysUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
public interface SysUserService {
    
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser queryUserByUserName(String userName);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    int updateUser(SysUser user);
}
