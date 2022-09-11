package com.beloved.common.model.dto.system;

import com.beloved.common.model.entity.system.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-26 14:45
 * @Description: 菜单DTO
 */
@Data
public class MenuDto extends SysMenu implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<MenuDto> children;
    
}
