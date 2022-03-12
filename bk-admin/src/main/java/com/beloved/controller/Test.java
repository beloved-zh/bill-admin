package com.beloved.controller;

import com.beloved.common.pojo.entity.SysUser;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class Test {

    @Resource
    private SysUserService userService;

    @GetMapping("/hello")
    public List<SysUser> test01() {
        log.debug("hello bk");
        return userService.selectUserList();
    }

}
