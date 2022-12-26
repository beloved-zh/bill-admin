package com.beloved.system.service.impl;

import com.beloved.common.model.dto.system.SysDictDto;
import com.beloved.common.model.entity.system.SysDictType;
import com.beloved.system.mapper.SysDictTypeMapper;
import com.beloved.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:28
 * @Description: 字典类型实现
 */
@Service
public class SysDictServiceImpl implements SysDictService {
    
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Override
    public List<SysDictType> queryTypeList(SysDictDto sysDictDto) {
        return dictTypeMapper.queryList(sysDictDto);
    }

    @Override
    public List<SysDictDto> queryDictDtoList(SysDictDto sysDictDto) {
        return dictTypeMapper.queryDictDtoList(sysDictDto);
    }
    
}
