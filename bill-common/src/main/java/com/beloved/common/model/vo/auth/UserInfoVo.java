package com.beloved.common.model.vo.auth;

import lombok.Data;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:20
 * @Description:
 */
@Data
public class UserInfoVo {

    private static final long serialVersionUID = 1L;
    
    private String userName;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String loginIp;
    private String loginDate;
    private List<String> roles;
}
