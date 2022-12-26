package com.beloved.common.converter;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.enums.StateEnum;
import com.beloved.common.model.dto.system.SysDictDto;
import com.beloved.common.model.entity.system.SysDictData;
import com.beloved.common.model.request.system.DictRequest;
import com.beloved.common.model.vo.common.OptionVo;
import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.BooleanUtils;
import com.beloved.common.utils.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-13 10:47
 * @Description: 字典类型转换
 */
@Mapper(componentModel = "spring", imports = {
    BaseEnum.class,
    StateEnum.class,
    MenuTypeEnum.class,
    BooleanUtils.class,
    StringUtils.class
})
public interface DictConverter {

    @Mappings({
        @Mapping(source = "dictLabel", target = "label"),
        @Mapping(source = "dictValue", target = "value")
    })
    OptionVo dictDataToOption(SysDictData dictData);

    List<OptionVo> dictDataToOptionList(List<SysDictData> dictDataList);
    
    SysDictDto requestToDto(DictRequest request);
}
