package com.beloved.system.mapper;

import com.beloved.common.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> queryList(SysRole role);
    
}
