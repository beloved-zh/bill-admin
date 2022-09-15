package com.beloved.common.model.request.common;

import com.github.pagehelper.IPage;
import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-14 14:29
 * @Description: 分页参数
 */
@Data
public class PageParams implements IPage {

    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private Integer pageNum = DEFAULT_PAGE_NUM;
    
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    
    private String orderBy;
    
}
