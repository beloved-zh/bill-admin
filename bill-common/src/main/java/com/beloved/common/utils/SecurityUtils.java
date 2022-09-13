package com.beloved.common.utils;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.dto.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * SpringSecurity 工具类
 *
 * @author beloved
 */
public class SecurityUtils {

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        try {
            return getLoginUser().getUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.GET_USER_FAIL);
        }
    }
    
    /**
     * 获取用户信息
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.GET_USER_FAIL);
        }
    }
    
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
