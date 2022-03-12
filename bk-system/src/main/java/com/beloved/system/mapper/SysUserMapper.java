package com.beloved.system.mapper;


import com.beloved.common.pojo.entity.SysUser;
import org.springframework.stereotype.Repository;

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

}
