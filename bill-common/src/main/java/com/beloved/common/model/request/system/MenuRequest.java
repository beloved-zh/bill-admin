package com.beloved.common.model.request.system;

import com.beloved.common.model.request.common.PageParams;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:10
 * @Description: 菜单查询请求实体
 */
@Data
public class MenuRequest extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String menuName;
    
    private String state;
    
}
