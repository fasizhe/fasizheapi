package com.faishze.api.fasizheapi.typehandler;

import com.faishze.api.fasizheapi.constant.ArticleType;
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
 * @description 文章类型转换
 * @contact 15521177704
 * @since 2019/10/25
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(value = ArticleType.class)
public class ArticleTypeHandler extends BaseTypeHandler<ArticleType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ArticleType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public ArticleType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return ArticleType.getArticleTypeById(id);
    }

    @Override
    public ArticleType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return ArticleType.getArticleTypeById(id);
    }

    @Override
    public ArticleType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return ArticleType.getArticleTypeById(id);
    }
}
