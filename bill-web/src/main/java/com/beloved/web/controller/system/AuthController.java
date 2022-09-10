package com.beloved.web.controller.system;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.converter.UserConverter;
import com.beloved.common.model.vo.system.CaptchaVo;
import com.beloved.common.model.vo.system.MenuTreeVo;
import com.beloved.common.model.vo.system.UserInfoVo;
import com.beloved.system.service.AuthService;
import com.beloved.system.service.CaptchaService;
import com.beloved.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private MenuConverter menuConverter;
    
    @GetMapping("/captcha")
    public CaptchaVo captcha() {
        return captchaService.createCaptcha();
    }

    @GetMapping("/getUserInfo")
    public UserInfoVo getUserinfo() {
        return userConverter.toVo(authService.getUserInfo());
    }
    
    @PostMapping("/getMenuTree")
    public List<MenuTreeVo> getMenuTree() {
        return menuConverter.toArraytoMenuTreeVo(authService.queryMenuTree());
    }
}
