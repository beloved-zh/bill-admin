package com.beloved.common.model.entity.system;

import com.beloved.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:05
 * @Description: 字典数据
 */
@Data
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典数据主键
     */
    private Long dictDataId;

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;
    
}
