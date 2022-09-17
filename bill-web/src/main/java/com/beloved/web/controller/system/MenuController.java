package com.beloved.web.controller.system;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.request.system.MenuRequest;
import com.beloved.common.valid.group.Query;
import com.beloved.system.service.SysMenuService;
import com.beloved.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:12
 * @Description:
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuConverter menuConverter;
    
    @Autowired
    private SysMenuService menuService;
    
    @PostMapping("/getMenuTree")
    public List<MenuDto> getMenuTree(@RequestBody @Validated({Query.class}) MenuRequest menuRequest) {
        return menuService.queryMenuTree(menuConverter.requestToMenu(menuRequest));
    }

    @PostMapping("/addMenu")
    public List<MenuDto> addMenu(@RequestBody MenuRequest menuRequest) {
        return menuService.queryMenuTree(menuConverter.requestToMenu(menuRequest));
    }
}   
