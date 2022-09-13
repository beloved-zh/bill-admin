package com.beloved.common.model.entity;

import com.beloved.common.annotation.FieldFill;
import com.beloved.common.enums.FieldFillType;
import com.beloved.common.enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-09 15:54
 * @Description: Entity基类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态（0停用 1正常）
     */
    private StateEnum state;
    
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    @FieldFill(FieldFillType.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldFill(FieldFillType.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @FieldFill(FieldFillType.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldFill(FieldFillType.INSERT_UPDATE)
    private Date updateTime;
    
}
