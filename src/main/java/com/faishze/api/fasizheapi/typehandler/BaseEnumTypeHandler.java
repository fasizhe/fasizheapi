package com.faishze.api.fasizheapi.typehandler;

import com.faishze.api.fasizheapi.constant.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杜科
 * @description 通用枚举转换
 * @contact 15521177704
 * @since 2019/10/24
 */
@MappedTypes(value = BaseEnum.class)
public class BaseEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
    private Class<E> enumType;
    private Map<Integer, E> enumMap = new HashMap<>();

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.enumType = type;

        E[] enums = enumType.getEnumConstants();
        if (enums == null)
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        for (E e : enums) {
            enumMap.put(e.getValue(), e);
        }
    }

    //用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    //用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return get(rs.getInt(columnName));
    }

    //用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return get(rs.getInt(columnIndex));
    }

    //用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return get(cs.getInt(columnIndex));
    }

    private E get(Integer v) {
        if (v == null) {
            return null;
        }

        return this.enumMap.get(v);
    }

}
