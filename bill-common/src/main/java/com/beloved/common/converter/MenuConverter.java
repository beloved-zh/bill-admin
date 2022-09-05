package com.beloved.common.converter;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.model.dto.MenuDto;
import com.beloved.common.model.entity.SysMenu;
import com.beloved.common.model.vo.auth.RouteVo;
import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.BooleanUtils;
import com.beloved.common.utils.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-26 16:13
 * @Description: 菜单实体转换
 */
@Mapper(componentModel = "spring", imports = {
    BaseEnum.class,
    MenuTypeEnum.class,
    BooleanUtils.class,
    StringUtils.class
})
public interface MenuConverter {

    @Mappings({
        @Mapping(target = "menuType", expression = "java(BaseEnum.getEnumByValue(menu.getMenuType(), MenuTypeEnum.class))"),
    })
    MenuDto toDto(SysMenu menu);

    List<MenuDto> toArrayDto(List<SysMenu> menuList);

    @Mappings({
        @Mapping(target = "name", expression = "java(StringUtils.toRootUpperCase(menuDto.getPath()))"),
        @Mapping(source = "path", target = "path"),
        @Mapping(source = "component", target = "component"),
        @Mapping(source = "menuName", target = "meta.title"),
        @Mapping(source = "icon", target = "meta.icon"),
        @Mapping(target = "meta.hidden", expression = "java(BooleanUtils.toBoolean(menuDto.getHidden()))"),
        @Mapping(target = "meta.fixed", expression = "java(BooleanUtils.toBoolean(menuDto.getFixed()))"),
        @Mapping(target = "meta.keepAlive", expression = "java(BooleanUtils.toBoolean(menuDto.getIsCache()))"),
    })
    RouteVo toRoute(MenuDto menuDto);

    List<RouteVo> toArrayRoute(List<MenuDto> menuList);
    
    default String getFindFirstChildrenPath(List<MenuDto> children) {

        Optional<MenuDto> menuDto = children.stream().findFirst();
        
        if (menuDto.isPresent()) {
            return menuDto.get().getPath();
        }

        return null;
    }
}
