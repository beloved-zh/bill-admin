package com.beloved.common.model.dto;

import com.beloved.common.model.entity.SysRole;
import com.beloved.common.model.entity.SysUser;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:11
 * @Description: 用户信息
 */
@Data
public class UserInfoDto {
    
    private String nickName;
    private String avatar;
    private String email;
    private String phoneNumber;
    private List<String> roles;
    
    public UserInfoDto(SysUser user) {
        this.nickName = user.getNickName();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles().stream().map(SysRole::getRoleName).collect(Collectors.toList());
    }
}
