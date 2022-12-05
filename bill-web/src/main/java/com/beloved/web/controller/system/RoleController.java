package com.beloved.web.controller.system;

import com.beloved.common.converter.RoleConverter;
import com.beloved.common.model.entity.system.SysRole;
import com.beloved.common.model.request.system.RoleRequest;
import com.beloved.system.service.SysRoleService;
import com.beloved.web.controller.common.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Beloved
 * @CreateTime: 2022-10-03 14:19
 * @Description:
 */
@Validated
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

   @Autowired
   private SysRoleService roleService;
   
   @Autowired 
   private RoleConverter roleConverter;
    
   @GetMapping("/getList")
   public PageInfo<SysRole> getList(RoleRequest roleRequest) {
      return super.startPage(roleRequest, () -> roleService.queryList(roleConverter.requestToRole(roleRequest)));
   }
    
}   
