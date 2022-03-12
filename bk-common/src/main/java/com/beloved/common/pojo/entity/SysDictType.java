package com.beloved.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 字典类型表(SysDictType)实体类
 *
 * @author beloved
 * @since 2022-03-12 14:17:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictType implements Serializable {

    private static final long serialVersionUID = -28674719237777391L;

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
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
}

