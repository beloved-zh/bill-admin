package com.beloved.system.service.impl;

import com.beloved.common.model.dto.system.SysDictTypeDto;
import com.beloved.system.mapper.SysDictTypeMapper;
import com.beloved.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:28
 * @Description: 字典类型实现
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    
    @Autowired
    private SysDictTypeMapper dictTypeMapper;
    
    @Override
    public SysDictTypeDto querySysDictTypeDataByType(String dictType) {
        return dictTypeMapper.querySysDictTypeDataByType(dictType);
    }
}
