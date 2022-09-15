package com.beloved.common.model.dto.system;

import com.beloved.common.model.entity.system.SysDictData;
import com.beloved.common.model.entity.system.SysDictType;
import lombok.Data;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:04
 * @Description: 字典类型
 */
@Data
public class SysDictTypeDto extends SysDictType {

    private static final long serialVersionUID = 1L;
    
    /**
     * 字典数据
     */
    private List<SysDictData> dictDataList;
    
}
