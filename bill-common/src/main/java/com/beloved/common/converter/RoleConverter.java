package com.beloved.common.converter;

import com.beloved.common.enums.BooleanEnum;
import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.enums.StateEnum;
import com.beloved.common.model.entity.system.SysRole;
import com.beloved.common.model.request.system.RoleRequest;
import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author: Beloved
 * @CreateTime: 2022-10-03 14:23
 * @Description: 角色实体转换
 */
@Mapper(componentModel = "spring", imports = {
    BaseEnum.class,
    StateEnum.class,
    MenuTypeEnum.class,
    BooleanEnum.class,
    StringUtils.class
})
public interface RoleConverter {
    
    @Mappings({
        @Mapping(target = "state", expression = "java(BaseEnum.getEnumByValue(request.getState(), StateEnum.class))"),
    })
    SysRole requestToRole(RoleRequest request);
}
