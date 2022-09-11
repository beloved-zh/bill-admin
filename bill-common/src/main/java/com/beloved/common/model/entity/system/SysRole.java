package com.beloved.common.model.entity.system;

import com.beloved.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Data
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 显示顺序
     */
    private Integer roleSort;


}
