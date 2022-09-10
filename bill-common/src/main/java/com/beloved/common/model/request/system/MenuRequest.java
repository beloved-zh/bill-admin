package com.beloved.common.model.request.system;

import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:10
 * @Description: 菜单查询请求实体
 */
@Data
public class MenuRequest {
    
    private String menuName;
    
    private String state;
    
}
