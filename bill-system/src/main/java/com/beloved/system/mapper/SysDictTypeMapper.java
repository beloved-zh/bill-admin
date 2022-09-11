package com.beloved.system.mapper;

import com.beloved.common.model.entity.system.SysDictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:24
 * @Description: 字典类型
 */
@Mapper
public interface SysDictTypeMapper {
    
    SysDictType querySysDictTypeByType(String dictType);
    
//    SysDictTypeDto querySysDictTypeDataByType(String dictType);
    Long add(SysDictType sysDictType);
}
