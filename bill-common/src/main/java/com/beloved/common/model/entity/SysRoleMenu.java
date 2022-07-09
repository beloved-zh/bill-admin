package com.beloved.common.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beloved.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableField("roleId")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField("menuId")
    private Long menuId;


}
