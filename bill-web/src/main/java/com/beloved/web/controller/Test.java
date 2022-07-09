package com.beloved.web.controller;

import com.beloved.common.annotation.RawData;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.entity.SysUser;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class Test {

    @Resource
    private SysUserService userService;

    @RawData
    @GetMapping("/test01")
    public List<SysUser> test01() {
        List<SysUser> list = userService.selectUserList();
        
        try {
            int a = 10 /0;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.UNAUTHORIZED);
        }
        
        return list;
    }

    @GetMapping("/test02")
    public String test02() {
        log.debug("test02");
        return "ResultVo.success(userService.selectUserList())";
    }

}
