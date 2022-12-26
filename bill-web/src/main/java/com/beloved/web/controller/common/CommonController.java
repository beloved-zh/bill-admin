package com.beloved.web.controller.common;

import com.beloved.common.converter.DictConverter;
import com.beloved.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-13 11:10
 * @Description: 通用处理
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private SysDictService dictTypeService;

    @Autowired
    private DictConverter dictConverter;
    
}
