package com.beloved.web.controller;

import com.beloved.common.enums.StateEnum;
import com.beloved.common.model.entity.system.SysDictType;
import com.beloved.system.mapper.SysDictTypeMapper;
import com.beloved.system.service.SysMenuService;
import com.beloved.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/test")
public class Test {

    @Resource
    private SysUserService userService;
    
    @Autowired
    private SysMenuService menuService;
    
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    
    @GetMapping("/data")
    public SysDictType getData() {
        SysDictType sysDictType = sysDictTypeMapper.querySysDictTypeByType("status");
        return sysDictType;
    }

    @GetMapping("/add")
    public String add() {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setDictName("测试");
        sysDictType.setDictType("test");
        sysDictType.setState(StateEnum.STOP);
        Long id = sysDictTypeMapper.add(sysDictType);
        log.debug("id：{}", id);
        return "成功";
    }
}
