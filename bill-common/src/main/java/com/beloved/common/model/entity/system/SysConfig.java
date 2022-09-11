package com.beloved.common.model.entity.system;

import com.beloved.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:05
 * @Description: 单值参数配置
 */
@Data
public class SysConfig extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    private Long configId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;
    
}
