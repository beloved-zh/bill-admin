package com.beloved.common.model.request.system;

import com.beloved.common.enums.StateEnum;
import com.beloved.common.valid.annotation.EnumValidator;
import com.beloved.common.valid.group.Create;
import com.beloved.common.valid.group.Query;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:10
 * @Description: 菜单查询请求实体
 */
@Data
public class MenuRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    @NotBlank(groups = {Query.class}, message = "菜单名称不能为空")
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
     * 是否缓存
     */
    private Boolean hasCache;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 是否固定tag
     */
    private Boolean fixed;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    private String menuType;
    
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单状态
     */
    @NotBlank(message = "菜单状态不能为空")
    @EnumValidator(enumType = StateEnum.class,groups = {Create.class}, message = "菜单状态参数不合法")
    private String state;
}
