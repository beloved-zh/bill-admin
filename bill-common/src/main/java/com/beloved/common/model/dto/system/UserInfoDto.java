package com.beloved.common.model.dto.system;

import com.beloved.common.model.entity.system.SysRole;
import com.beloved.common.model.entity.system.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:11
 * @Description: 用户信息
 */
@Data
public class UserInfoDto extends SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<SysRole> roles;
    
}
