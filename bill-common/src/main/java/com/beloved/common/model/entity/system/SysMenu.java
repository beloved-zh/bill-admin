package com.beloved.common.model.entity.system;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.enums.WhetherEnum;
import com.beloved.common.model.entity.BaseEntity;
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
     * iframe 标签地址
     */
    private String iframePath;

    /**
     * 是否缓存（0否 1是）
     */
    private WhetherEnum hasCache;

    /**
     * 是否隐藏（0否 1是）
     */
    private WhetherEnum hidden;

    /**
     * 是否固定tag（0否 1是）
     */
    private WhetherEnum fixed;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    private MenuTypeEnum menuType;
    

    /**
     * 菜单图标
     */
    private String icon;


}
