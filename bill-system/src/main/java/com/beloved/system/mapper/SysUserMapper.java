package com.beloved.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beloved.common.model.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
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

    /**
     * 
     */
    
}
