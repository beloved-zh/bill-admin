package com.beloved.core.handler.mybatis;

import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 20:12
 * @Description: mybatis 枚举处理
 */
@Slf4j
public class EnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {
    
    private final String DEFAULT_DATA_NAME = "value";
    
    private ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
    private final Class<E> enumClassType;
    private final Class<?> propertyType;
    
    public EnumTypeHandler(Class<E> enumClassType) {
        if (enumClassType == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enumClassType = enumClassType;
        this.propertyType = MetaClass.forClass(enumClassType, reflectorFactory).getGetterType(DEFAULT_DATA_NAME);
    }
    
    /**
     * 用于定义设置参数时，该如何把 Java 类型的参数转换为对应的数据库类型
     * @param ps
     * @param paramIndex
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int paramIndex, E parameter, JdbcType jdbcType) throws SQLException {
        if (ObjectUtils.isEmpty(jdbcType)) {
            ps.setObject(paramIndex, parameter.getValue());
        } else {
            ps.setObject(paramIndex, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的 Java 类型
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName, this.propertyType);
        if (ObjectUtils.isEmpty(value) && rs.wasNull()) {
            return null;
        }
        return BaseEnum.getEnumByValue(value, this.enumClassType);
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex, this.propertyType);
        if (ObjectUtils.isEmpty(value) && rs.wasNull()) {
            return null;
        }
        return BaseEnum.getEnumByValue(value, this.enumClassType);
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的 Java 类型
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex, this.propertyType);
        if (ObjectUtils.isEmpty(value) && cs.wasNull()) {
            return null;
        }
        return BaseEnum.getEnumByValue(value, this.enumClassType);
    }
}
