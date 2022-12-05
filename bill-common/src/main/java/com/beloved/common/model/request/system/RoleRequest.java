package com.beloved.common.model.request.system;

import com.beloved.common.model.request.common.PageParams;
import lombok.Data;

import java.io.Serializable;

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
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 菜单状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;
    
}
