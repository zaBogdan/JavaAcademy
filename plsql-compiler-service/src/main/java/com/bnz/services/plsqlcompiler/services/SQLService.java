package com.bnz.services.plsqlcompiler.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SQLService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataSource dataSource;

    public String execute(String stmt) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                                                .withFunctionName("executeQuery")
                                                .withReturnValue();

        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_query", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_userId", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlOutParameter("response", Types.VARCHAR));

        Map<String, Object> params = new HashMap<>();
        params.put("v_query", stmt);
        params.put("v_userId", "zaBogdan");
        SqlParameterSource in = new MapSqlParameterSource(params);
        Map<String, Object> response = simpleJdbcCall.execute(in);
        System.out.println(response.get("response"));

        return (String) response.get("response");
    }
}
