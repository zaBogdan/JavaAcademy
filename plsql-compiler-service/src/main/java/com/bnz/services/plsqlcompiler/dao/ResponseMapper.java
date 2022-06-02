package com.bnz.services.plsqlcompiler.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseMapper implements RowMapper<Response> {
    public Response mapRow(ResultSet rs, int rowNum) throws SQLException {
        Response student = new Response();
        student.setResponse(rs.getString("response"));
        student.setV_query(rs.getString("v_query"));
        student.setV_userId(rs.getString("v_UserId"));
        return student;
    }
}
