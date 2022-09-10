package com.beloved.common.model.dto.system;

import com.beloved.common.model.entity.system.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 16:11
 * @Description: 用户信息
 */
@Data
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long userId;
    private String userName;
    private String nickName;
    private String email;
    private String phoneNumber;
    private Integer sex;
    private String avatar;
    private String password;
    private Integer state;
    private String loginIp;
    private Date loginDate;
    private List<SysRole> roles;
    
}
