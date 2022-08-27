package com.beloved.common.model.vo.auth;

import lombok.Data;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-04 17:18
 * @Description: 菜单路由实体
 */
@Data
public class RouteVo {
    
    private String path;

    private String component;

    private String redirect;

    private Meta meta;

    @Data
    public static class Meta {

        private String title;

        private String icon;
        
        /**
         * 如果设置为 true，目录没有子节点也会显示
         */
        private Boolean alwaysShow;

        /**
         * 页面缓存开启状态
         */
        private Boolean keepAlive;
    }

    private List<RouteVo> children;
}
