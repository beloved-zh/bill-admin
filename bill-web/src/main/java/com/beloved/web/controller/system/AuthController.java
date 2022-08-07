package com.beloved.web.controller.system;

import com.beloved.common.model.dto.CaptchaDto;
import com.beloved.common.model.vo.auth.RouteVo;
import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.common.utils.ObjectUtils;
import com.beloved.system.service.AuthService;
import com.beloved.system.service.CaptchaService;
import com.beloved.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-27 14:29
 * @Description:
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    
    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    private AuthService authService;
    
    
    @GetMapping("/captcha")
    public CaptchaDto captcha() {
        return captchaService.createCaptcha();
    }

    @GetMapping("/getUserInfo")
    public UserInfoDto getUserinfo() {
        return authService.getUserInfo();
    }
    
    @PostMapping("/getRoutes")
    public RouteVo getRoutes(Long parentId) {
        if (ObjectUtils.isEmpty(parentId)) {
            parentId = 0L;
        }
        return null;
    }
}
