package com.faishze.api.fasizheapi.typehandler;

import com.faishze.api.fasizheapi.enums.OauthType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author masonluo
 * @date 2019/11/1 12:09 AM
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(OauthType.class)
public class OauthTypeHandler extends BaseTypeHandler<OauthType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OauthType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getOauthName());
    }

    @Override
    public OauthType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String type = rs.getString(columnName);
        return OauthType.getOauthTypeByOauthName(type);
    }

    @Override
    public OauthType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String type = rs.getString(columnIndex);
        return OauthType.getOauthTypeByOauthName(type);
    }

    @Override
    public OauthType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String type = cs.getString(columnIndex);
        return OauthType.getOauthTypeByOauthName(type);
    }
}
