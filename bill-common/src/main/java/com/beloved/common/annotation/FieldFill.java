package com.beloved.common.annotation;

import com.beloved.common.enums.FieldFillType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-12 12:31
 * @Description: 字段填充
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD})
public @interface FieldFill {

    FieldFillType value();
    
}
