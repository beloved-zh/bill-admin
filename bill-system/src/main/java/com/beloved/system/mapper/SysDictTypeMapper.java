package com.beloved.system.mapper;

import com.beloved.common.model.dto.system.SysDictDto;
import com.beloved.common.model.entity.system.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:24
 * @Description: 字典类型
 */
@Mapper
public interface SysDictTypeMapper {
    
    List<SysDictType> queryList(SysDictDto sysDictDto);
    
    List<SysDictDto> queryDictDtoList(SysDictType sysDictType);
}
