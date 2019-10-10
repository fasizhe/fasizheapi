package com.faishze.api.fasizheapi.typehandler;

import com.faishze.api.fasizheapi.constant.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Enum性别类型转整型
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */

/**
 * 声明jdbcType为整型
 * 声明JavaType为Gender
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = Gender.class)
public class GenderTypeHandler extends BaseTypeHandler<Gender> {

    /**
     * 设置非空性别参数
     *
     * @param ps
     * @param i
     * @param gender
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, gender.getId());
    }

    /**
     * 通过列名读取性别
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return Gender.getGenderById(id);
    }

    /**
     * 通过下标读取性别
     *
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return Gender.getGenderById(id);
    }

    /**
     * 通过存储过程读取性别
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return Gender.getGenderById(id);
    }
}
