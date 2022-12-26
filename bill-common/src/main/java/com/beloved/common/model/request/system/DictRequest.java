package com.beloved.common.model.request.system;

import com.beloved.common.model.request.common.PageParams;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Beloved
 * @CreateTime: 2022-12-12 16:07
 * @Description:
 */
@Data
public class DictRequest extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;
    
}
