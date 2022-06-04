package com.bnz.services.plsqlcompiler.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class SQLService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataSource dataSource;

    public String execute(String stmt) throws ResponseStatusException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                                                .withCatalogName("plsql_service")
                                                .withFunctionName("executeQuery")
                                                .withReturnValue();
        String table = stmt.toLowerCase().split("from ")[1].split(" ")[0];

        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_tableName", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_query", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_userId", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlOutParameter("response", Types.VARCHAR));

        Map<String, Object> params = new HashMap<>();
        params.put("v_tableName", table);
        params.put("v_query", stmt);
        params.put("v_userId", "zaBogdan");
        try {
            SqlParameterSource in = new MapSqlParameterSource(params);
            Map<String, Object> response = simpleJdbcCall.execute(in);
            return (String) response.get("response");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }

    public String statistics(String userId) throws ResponseStatusException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("plsql_service")
                .withFunctionName("getUserStatistics")
                .withReturnValue();

        simpleJdbcCall.addDeclaredParameter(new SqlParameter("v_userId", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlOutParameter("response", Types.VARCHAR));

        Map<String, Object> params = new HashMap<>();
        params.put("v_userId", userId);

        try {
            SqlParameterSource in = new MapSqlParameterSource(params);
            Map<String, Object> response = simpleJdbcCall.execute(in);
            return (String) response.get("response");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }
}
