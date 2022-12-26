package com.beloved.web.controller.system;

import com.beloved.common.converter.RoleConverter;
import com.beloved.common.model.entity.system.SysRole;
import com.beloved.common.model.request.system.RoleRequest;
import com.beloved.common.valid.group.crud.Create;
import com.beloved.common.valid.group.crud.Update;
import com.beloved.common.valid.group.system.role.AuthMenus;
import com.beloved.system.service.SysRoleService;
import com.beloved.web.controller.common.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
   
   @PostMapping("/save")
   public long saveRole(@RequestBody @Validated({ Create.class }) RoleRequest roleRequest) {
      return roleService.saveRole(roleConverter.requestToRole(roleRequest));
   }

   @PutMapping("/edit")
   public long editRole(@RequestBody @Validated({ Update.class }) RoleRequest roleRequest) {
      return roleService.editRole(roleConverter.requestToRole(roleRequest));
   }

   @GetMapping("/getRoleAuthMenus/{roleId}")
   public List<Long> getRoleAuthMenus(@PathVariable("roleId") @NotNull(message = "角色id不能为空") long roleId) {
      return roleService.getRoleAuthMenus(roleId);
   }

   @PostMapping("/authRoleMenus")
   public void authRoleMenus(@RequestBody @Validated({ AuthMenus.class }) RoleRequest roleRequest) {
      roleService.authRoleMenus(roleConverter.requestToDto(roleRequest));
   }

   @DeleteMapping("/remove/{roleId}")
   public void removeRole(@PathVariable("roleId") @NotNull(message = "角色id不能为空") long roleId) {
      roleService.removeRole(roleId);
   }
}   
