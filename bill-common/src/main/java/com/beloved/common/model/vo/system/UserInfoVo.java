package com.beloved.common.model.vo.system;

import lombok.Data;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:20
 * @Description: 登录用户信息
 */
@Data
public class UserInfoVo {

    private static final long serialVersionUID = 1L;
    
    private String userName;
    private String nickName;
    private String sex;
    private String avatar;
    private List<String> roles;
}
