package com.beloved.common.model.request.system;

import com.beloved.common.model.request.common.PageParams;
import com.beloved.common.valid.group.crud.Create;
import com.beloved.common.valid.group.crud.Update;
import com.beloved.common.valid.group.system.role.AuthMenus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-10-03 14:21
 * @Description: 
 */
@Data
public class RoleRequest extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull(groups = { Update.class, AuthMenus.class }, message = "角色ID不能为空")
    private Long roleId;

    /**
     * 角色名称
     */
    @NotNull(groups = { Create.class, Update.class }, message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色标识
     */
    @NotNull(groups = { Create.class, Update.class }, message = "角色标识不能为空")
    private String roleCode;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    @NotNull(groups = { AuthMenus.class }, message = "菜单列表不能为空")
    private List<Long> menus;
}
