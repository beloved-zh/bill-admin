package com.beloved.common.model.entity.system;

import com.beloved.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:04
 * @Description: 字典类型
 */
@Data
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;
    
}
