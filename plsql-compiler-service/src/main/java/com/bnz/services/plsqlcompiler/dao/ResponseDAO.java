package com.bnz.services.plsqlcompiler.dao;

import javax.sql.DataSource;

public interface ResponseDAO {
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    void setDataSource(DataSource ds);

    /**
     * This is the method to be used to list down
     * a record from the Student table corresponding
     * to a passed student id.
     */
    Response getStudent(Integer id);
}
