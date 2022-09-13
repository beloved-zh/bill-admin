package com.beloved.core.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.beloved.common.annotation.FieldFill;
import com.beloved.common.enums.FieldFillType;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.utils.SecurityUtils;
import com.beloved.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-12 11:45
 * @Description:
 */
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisAutoFillInterceptor implements Interceptor {
    
    private final String DEFAULT_USER = "SYSTEM";
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        
        // 获取 SQL 命令
        SqlCommandType sqlCommandType = ((MappedStatement) invocation.getArgs()[0]).getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        List<Field> autoFillField = this.getAutoFillField(parameter);

        autoFillField.forEach(field -> {
            FieldFillType fieldFillType = field.getAnnotation(FieldFill.class).value();
            if ((sqlCommandType.equals(SqlCommandType.INSERT) && fieldFillType.equals(FieldFillType.INSERT)) || fieldFillType.equals(FieldFillType.INSERT_UPDATE)) {
                this.insertHandler(parameter, field);
            }
            if ((sqlCommandType.equals(SqlCommandType.UPDATE) && fieldFillType.equals(FieldFillType.UPDATE)) || fieldFillType.equals(FieldFillType.INSERT_UPDATE)) {
                this.updateHandler(parameter, field);
            }
        });

        
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    private List<Field> getAutoFillField(Object object) {
        Field[] fields = ReflectUtil.getFieldsDirectly(object.getClass(), true);
        List<Field> fieldFill = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(FieldFill.class))
                .collect(Collectors.toList());
        
        return fieldFill; 
    }
    
    private void insertHandler(Object object, Field field) {
        if (StringUtils.equals(field.getName(), "createBy")) {
            try {
                ReflectUtil.setFieldValue(object, field, SecurityUtils.getUserId());
            } catch (ServiceException e) {
                ReflectUtil.setFieldValue(object, field, DEFAULT_USER);
            }
        }
        if (StringUtils.endsWith(field.getName(), "createTime")) {
            ReflectUtil.setFieldValue(object, field, new Date());
        }
    }

    private void updateHandler(Object object, Field field) {
        if (StringUtils.equals(field.getName(), "updateBy")) {
            try {
                ReflectUtil.setFieldValue(object, field, SecurityUtils.getUserId());
            } catch (ServiceException e) {
                ReflectUtil.setFieldValue(object, field, DEFAULT_USER);
            }
        }
        if (StringUtils.endsWith(field.getName(), "updateTime")) {
            ReflectUtil.setFieldValue(object, field, new Date());
        }
    }
}
