package com.beloved.web.controller.common;

import com.beloved.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-27 14:33
 * @Description: 通用处理
 */
@Slf4j
public class BaseController {

    /**
     * 处理时间参数
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
}
