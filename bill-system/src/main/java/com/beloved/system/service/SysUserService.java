package com.beloved.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beloved.common.model.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
public interface SysUserService extends IService<SysUser> {

    List<SysUser> selectUserList();

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser queryUserByUserName(String userName);
}
