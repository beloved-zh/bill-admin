package com.beloved.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.beloved.common.vo.ResultVo;
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

    @GetMapping("/test01")
    public JSONObject test01() {
        log.debug("test01");
        return ResultVo.success();
    }

    @GetMapping("/test02")
    public JSONObject test02() {
        log.debug("test02");
        return ResultVo.success(userService.selectUserList());
    }

}
