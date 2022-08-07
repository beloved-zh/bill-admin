package com.beloved.web.controller;

import com.beloved.common.model.entity.SysMenu;
import com.beloved.common.model.entity.SysUser;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class Test {

    @Resource
    private SysUserService userService;
    
    @Autowired
    private SysMenuService menuService;
    
    @GetMapping("/test01")
    public SysUser test02() {
        log.debug("test01");
        return userService.queryUserByUserName("admin");
    }

    @GetMapping("/queryMenuList")
    public List<SysMenu> queryMenuList() {
        return menuService.queryMenuList(null);
    }
}
