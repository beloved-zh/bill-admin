package com.beloved.system.security.handle;

import com.alibaba.fastjson2.JSON;
import com.beloved.common.model.ResultVo;
import com.beloved.common.model.dto.TokenDto;
import com.beloved.common.model.entity.SysUser;
import com.beloved.common.utils.ServletUtils;
import com.beloved.system.security.bo.LoginUser;
import com.beloved.system.security.bo.TokenConfig;
import com.beloved.system.security.service.TokenService;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义认证成功处理器
 *
 * @author beloved
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private TokenConfig tokenConfig;
    
    @Autowired
    private SysUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        
        String token = tokenService.createToken(loginUser);

        SysUser user = loginUser.getUser();
        user.setLoginIp(ServletUtils.getClientIP(request));
        user.setLoginDate(new Date());
        userService.updateUser(user);
        
        TokenDto tokenDto = new TokenDto();
        tokenDto.setHeader(tokenConfig.getHeader());
        tokenDto.setTokenPrefix(tokenConfig.getTokenPrefix());
        tokenDto.setToken(token);
        ServletUtils.writeJson(response, JSON.toJSONString(new ResultVo<TokenDto>(tokenDto)));
    }

}
