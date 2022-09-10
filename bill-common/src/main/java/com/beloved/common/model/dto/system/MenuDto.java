package com.beloved.common.model.dto.system;

import com.beloved.common.enums.MenuTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-26 14:45
 * @Description: 菜单DTO
 */
@Data
public class MenuDto {

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
     * 是否缓存（0不缓存 1缓存）
     */
    private Integer hasCache;

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
    private MenuTypeEnum menuType;

    /**
     * 菜单状态（0停用 1正常）
     */
    private Integer state;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    private List<MenuDto> children;
}
