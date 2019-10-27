package com.faishze.api.fasizheapi.typehandler;

import com.faishze.api.fasizheapi.constant.HistoryRecordType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 杜科
 * @description 历史记录类型转换
 * @contact 15521177704
 * @since 2019/10/25
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = HistoryRecordType.class)
public class HistoryRecordTypeHandler extends BaseTypeHandler<HistoryRecordType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, HistoryRecordType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public HistoryRecordType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return HistoryRecordType.getHistoryRecordTypeById(id);
    }

    @Override
    public HistoryRecordType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return HistoryRecordType.getHistoryRecordTypeById(id);
    }

    @Override
    public HistoryRecordType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return HistoryRecordType.getHistoryRecordTypeById(id);
    }
}
