package com.beloved.common.model.entity;

import com.beloved.common.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Data
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
