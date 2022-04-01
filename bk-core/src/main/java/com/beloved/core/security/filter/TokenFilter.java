package com.beloved.core.security.filter;

import com.beloved.common.entity.SysUser;
import com.beloved.common.utils.ObjectUtils;
import com.beloved.core.security.bo.LoginUser;
import com.beloved.core.security.service.TokenService;
import com.beloved.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author beloved
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String loginUser = tokenService.getLoginUser(request);
        String loginUser = null;
        if (ObjectUtils.isNotEmpty(loginUser)) {
            // TODO 从 redis 获取用户对象 目前先从库里获取
            LoginUser user = new LoginUser(userService.queryUserByUserName(loginUser));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
