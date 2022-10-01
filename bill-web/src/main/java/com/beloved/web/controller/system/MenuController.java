package com.beloved.web.controller.system;

import com.beloved.common.converter.MenuConverter;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.request.system.MenuRequest;
import com.beloved.common.valid.group.crud.Create;
import com.beloved.common.valid.group.crud.Update;
import com.beloved.system.service.SysMenuService;
import com.beloved.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:12
 * @Description:
 */
@Validated
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuConverter menuConverter;
    
    @Autowired
    private SysMenuService menuService;
    
    @GetMapping("/getMenuTree")
    public List<MenuDto> getMenuTree(MenuRequest menuRequest) {
        return menuService.queryMenuTree(menuConverter.requestToMenu(menuRequest));
    }

    @PostMapping("/save")
    public Long addMenu(@RequestBody @Validated({ Create.class }) MenuRequest menuRequest) {
        return menuService.saveMenu(menuConverter.requestToMenu(menuRequest));
    }

    @PutMapping("/edit")
    public Long editMenu(@RequestBody @Validated({ Update.class }) MenuRequest menuRequest) {
        return menuService.editMenu(menuConverter.requestToMenu(menuRequest));
    }

    @DeleteMapping("/remove/{menuId}")
    public void removeMenu(@PathVariable("menuId") @NotNull(message = "菜单id不能为空") Long menuId) {
        menuService.removeMenu(menuId);
    }
    
}   
