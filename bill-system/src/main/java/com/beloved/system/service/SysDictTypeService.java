package com.beloved.system.service;

import com.beloved.common.model.dto.system.SysDictTypeDto;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:28
 * @Description: 字典类型
 */
public interface SysDictTypeService {

    SysDictTypeDto querySysDictTypeDataByType(String dictType);
    
}
