package com.night.handler;

import com.night.bean.enums.DeleteEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author night
 */
@MappedTypes(DeleteEnum.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class DeleteHandler extends BaseTypeHandler<DeleteEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DeleteEnum deleteEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, deleteEnum.type());
    }

    @Override
    public DeleteEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int type = resultSet.getInt(s);
        return DeleteEnum.parse(type);
    }

    @Override
    public DeleteEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int type = resultSet.getInt(i);
        return DeleteEnum.parse(type);
    }

    @Override
    public DeleteEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
