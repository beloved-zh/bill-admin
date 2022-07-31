package com.beloved.common.model.entity;

import com.beloved.common.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Data
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
