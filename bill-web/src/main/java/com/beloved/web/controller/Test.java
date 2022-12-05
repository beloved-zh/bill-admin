package com.beloved.web.controller;

import com.beloved.system.mapper.SysDictTypeMapper;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Validated
@Controller
@RequestMapping("/test")
public class Test {

    @Resource
    private SysUserService userService;
    
    @Autowired
    private SysMenuService menuService;
    
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @RequestMapping(value="/test001", method = RequestMethod.POST)
    @ResponseBody
    public String inspectionDone(String test){
       
        return test;
    }
}
