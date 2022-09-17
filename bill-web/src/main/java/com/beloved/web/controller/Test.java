package com.beloved.web.controller;

import com.beloved.common.model.request.system.MenuRequest;
import com.beloved.system.mapper.SysDictTypeMapper;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
@RestController
@RequestMapping("/test")
public class Test {

    @Resource
    private SysUserService userService;
    
    @Autowired
    private SysMenuService menuService;
    
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    
    @GetMapping("/test01")
    public String getData(@RequestParam @NotBlank(message = "消息不能为空") String msg,
                          @Max(value = 100, message = "年龄最大不能超过100") @RequestParam("age") Integer age) {
        return msg;
    }

    @GetMapping("/test02/{name}")
    public String getData(@PathVariable("name") @Max(100) Integer name) {
        return "OK";
    }

    @GetMapping("/test03")
    public MenuRequest getData(@Validated MenuRequest request) {
        return request;
    }
}
