package com.beloved.common.model.vo.auth;

import lombok.Data;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 17:18
 * @Description: 菜单路由实体
 */
@Data
public class MenuTreeVo {
    
    private String name;
    
    private String path;

    private String component;

    private Meta meta;

    @Data
    public static class Meta {

        private String title;

        private String icon;
        
        private Boolean hidden;
        
        private Boolean fixed;
        
        private Boolean keepAlive;
    }

    private List<MenuTreeVo> children;
}
