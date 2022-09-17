package com.beloved.common.converter;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.enums.StateEnum;
import com.beloved.common.enums.WhetherEnum;
import com.beloved.common.model.dto.system.MenuDto;
import com.beloved.common.model.entity.system.SysMenu;
import com.beloved.common.model.request.system.MenuRequest;
import com.beloved.common.model.vo.system.MenuTreeVo;
import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-26 16:13
 * @Description: 菜单实体转换
 */
@Mapper(componentModel = "spring", imports = {
    BaseEnum.class,
    StateEnum.class,
    MenuTypeEnum.class,
    WhetherEnum.class,
    StringUtils.class
})
public interface MenuConverter {
    
    MenuDto toDto(SysMenu menu);

    List<MenuDto> toArrayDto(List<SysMenu> menuList);

    @Mappings({
        @Mapping(target = "name", expression = "java(StringUtils.toRootUpperCase(menuDto.getPath()))"),
        @Mapping(source = "path", target = "path"),
        @Mapping(source = "component", target = "component"),
        @Mapping(source = "menuName", target = "meta.title"),
        @Mapping(source = "icon", target = "meta.icon"),
        @Mapping(target = "meta.hidden", expression = "java(menuDto.getHidden().getLabel())"),
        @Mapping(target = "meta.fixed", expression = "java(menuDto.getFixed().getLabel())"),
        @Mapping(target = "meta.keepAlive", expression = "java(menuDto.getHasCache().getLabel())"),
        @Mapping(source = "iframePath", target = "meta.iframe")
    })
    MenuTreeVo toMenuTreeVo(MenuDto menuDto);

    List<MenuTreeVo> toArraytoMenuTreeVo(List<MenuDto> menuList);

    @Mappings({
        @Mapping(target = "hasCache", expression = "java(BaseEnum.getEnumByValue(request.getHasCache(), WhetherEnum.class))"),
        @Mapping(target = "hidden", expression = "java(BaseEnum.getEnumByValue(request.getHidden(), WhetherEnum.class))"),
        @Mapping(target = "fixed", expression = "java(BaseEnum.getEnumByValue(request.getFixed(), WhetherEnum.class))"),
        @Mapping(target = "menuType", expression = "java(BaseEnum.getEnumByValue(request.getMenuType(), MenuTypeEnum.class))"),
        @Mapping(target = "state", expression = "java(BaseEnum.getEnumByValue(request.getState(), StateEnum.class))"),
    })
    SysMenu requestToMenu(MenuRequest request);
}
