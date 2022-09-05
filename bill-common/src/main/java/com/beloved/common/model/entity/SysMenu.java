package com.beloved.common.model.entity;

import com.beloved.common.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Data
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;
    
    /**
     * 是否为外链（0否 1是）
     */
    private Integer isFrame;

    /**
     * 是否缓存（0不缓存 1缓存）
     */
    private Integer isCache;

    /**
     * 是否隐藏（0否 1是）
     */
    private Integer hidden;

    /**
     * 是否固定tag（0否 1是）
     */
    private Integer fixed;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0停用 1正常）
     */
    private Integer status;

    /**
     * 菜单图标
     */
    private String icon;


}
