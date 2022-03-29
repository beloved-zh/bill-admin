package com.beloved.admin.controller;

import com.beloved.common.enums.ResultCode;
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

    @GetMapping("/hello")
    public ResultVo test01() {
        log.debug("hello bk");
        return ResultVo.msg(ResultCode.TEST, userService.selectUserList());
    }

}
