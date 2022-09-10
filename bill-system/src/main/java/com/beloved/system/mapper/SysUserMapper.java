package com.beloved.system.mapper;

import com.beloved.common.model.dto.system.UserInfoDto;
import com.beloved.common.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Mapper
public interface SysUserMapper {
    
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    UserInfoDto queryUserByUserName(String userName);
    
    int updateUser(SysUser user);
}
