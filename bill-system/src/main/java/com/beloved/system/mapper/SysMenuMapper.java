package com.beloved.system.mapper;

import com.beloved.common.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Mapper
public interface SysMenuMapper {

    List<SysMenu> queryMenuListByUserId(Long userId);
    
    SysMenu queryMenuById(Long id);

    List<SysMenu> queryChildrenMenuByParentId(Long parentId);
    
    List<SysMenu> queryMenuList(SysMenu menu);
    
    Long saveMenu(SysMenu menu);

    Long editMenu(SysMenu menu);

    void removeMenu(Long menuId);
}
