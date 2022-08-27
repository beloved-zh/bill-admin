package com.beloved.common.converter;

import com.beloved.common.enums.GenderEnum;
import com.beloved.common.model.dto.UserInfoDto;
import com.beloved.common.model.entity.SysRole;
import com.beloved.common.model.entity.SysUser;
import com.beloved.common.model.vo.auth.UserInfoVo;
import com.beloved.common.service.BaseEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.stream.Collectors;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 11:10
 * @Description: 用户转换器
 */
@Mapper(componentModel = "spring", imports = {
    BaseEnum.class,
    GenderEnum.class,
    SysRole.class,
    Collectors.class
})
public interface UserConverter {
    
    SysUser toSysUser(UserInfoDto userInfoDto);

    @Mappings({
        @Mapping(target = "sex", expression = "java(BaseEnum.getLabelByValue(userInfo.getSex(), GenderEnum.class))"),
        @Mapping(target = "roles", expression = "java(userInfo.getRoles().stream().map(SysRole::getRoleName).collect(Collectors.toList()))")
    })
    UserInfoVo toVo(UserInfoDto userInfo);
}
