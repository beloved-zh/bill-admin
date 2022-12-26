package com.beloved.common.model.dto.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-12-26 09:50
 * @Description:
 */
@Data
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private List<Long> menus;
}
