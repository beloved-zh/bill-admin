package com.beloved.web.controller;

import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class Test {

    @Resource
    private SysUserService userService;
    
    @GetMapping("/test02")
    public String test02() {
        log.debug("test02");
        return "ResultVo.success(userService.selectUserList())";
    }

}
