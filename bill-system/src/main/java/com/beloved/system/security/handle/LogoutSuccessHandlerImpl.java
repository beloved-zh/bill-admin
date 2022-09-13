package com.beloved.system.security.handle;

import com.alibaba.fastjson2.JSON;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.enums.ResultCode;
import com.beloved.common.model.vo.ResultVo;
import com.beloved.common.utils.ObjectUtils;
import com.beloved.common.utils.ServletUtils;
import com.beloved.common.model.dto.security.LoginUser;
import com.beloved.system.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-03 09:42
 * @Description: 注销成功处理器
 */
@Slf4j
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;
    
    /**
     * 不经过 TokenFilter 无法设置用户信息，所以 authentication 为空，需要从 token中获取用户信息
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        LoginUser user = tokenService.getLoginUser(request);
        if (ObjectUtils.isEmpty(user)) {
            throw new AuthenticationCredentialsNotFoundException(ErrorCode.UNAUTHORIZED.getMessage());
        }
        // 删除用户缓存记录
        tokenService.clearLoginUser(user.getLonginId());
        log.debug("用户：{} 注销成功", user.getUsername());
        ServletUtils.writeJson(response, JSON.toJSONString(new ResultVo<>(ResultCode.LOGOUT_SUCCESS)));
    }
    
}
