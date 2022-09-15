package com.beloved.system.mapper;

import com.beloved.common.model.entity.system.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:25
 * @Description: 字典数据
 */
@Mapper
public interface SysDictDataMapper {

    List<SysDictData> queryListByDictId(Long dictId);
    
}
