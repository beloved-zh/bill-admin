package com.beloved.system.service;

import com.beloved.common.model.dto.system.SysDictDto;
import com.beloved.common.model.entity.system.SysDictType;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:28
 * @Description: 字典类型
 */
public interface SysDictService {

    List<SysDictType> queryTypeList(SysDictDto sysDictDto);
    
    List<SysDictDto> queryDictDtoList(SysDictDto sysDictDto);

}
