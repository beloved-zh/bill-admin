package com.beloved.system.security.bo;

import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.common.utils.BooleanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户
 *
 * @author beloved
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    public LoginUser(UserInfoDto user) {
        this.user = user;
    }

    /**
     * 用户信息
     */
    private UserInfoDto user;

    /**
     * 用户登录唯一标识
     */
    private String longinId;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取密码
     * @return
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * 获取用户名
     * @return
     */
    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    /**
     * 账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否激活
     * @return
     */
    @Override
    public boolean isEnabled() {
        return !BooleanUtils.toBoolean(this.user.getStatus());
    }
}
