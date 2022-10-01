package com.beloved.common.model.request.system;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.valid.annotation.EnumValidator;
import com.beloved.common.valid.group.crud.Create;
import com.beloved.common.valid.group.crud.Update;
import com.beloved.common.valid.group.system.menu.*;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:10
 * @Description: 菜单查询请求实体
 */
@Data
@GroupSequenceProvider(value = MenuRequestGroupSequenceProvider.class)
public class MenuRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 菜单ID
     */
    @NotNull(groups = { Update.class }, message = "菜单ID不能为空")
    private Long menuId;

    /**
     * 菜单标题
     */
    @NotNull(groups = { Create.class, Update.class }, message = "菜单标题不能为空")
    private String menuTitle;
    
    /**
     * 菜单名称
     */
    @NotNull(groups = { Create.class, Update.class }, message = "菜单名称不能为空")
    private String menuName;

    /**
     * 父菜单ID
     */
    @NotNull(groups = { Create.class }, message = "父菜单不能为空")
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(groups = { Create.class, Update.class }, message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @NotNull(groups = { AddDir.class, AddMenu.class, EditDir.class, EditMenu.class}, message = "路由地址不能为空")
    private String path;

    /**
     * 组件路径
     */
    @NotNull(groups = { AddMenu.class, EditMenu.class }, message = "组件地址不能为空")
    private String component;
    
    /**
     * 是否缓存
     */
    private Integer hasCache;

    /**
     * 是否隐藏
     */
    private Integer hidden;

    /**
     * 是否固定tag
     */
    private Integer fixed;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    @NotNull(groups = { Create.class, Update.class }, message = "菜单状态不能为空")
    @EnumValidator(enumType = MenuTypeEnum.class, groups = { Create.class, Update.class }, message = "菜单状态参数不合法")
    private String menuType;
    
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;
    
}
