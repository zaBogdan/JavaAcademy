package com.bnz.services.plsqlcompiler.dao;

public class Response {
    private String v_query;
    private String v_userId;
    private String response;

    public String getV_query() {
        return v_query;
    }

    public void setV_query(String v_query) {
        this.v_query = v_query;
    }

    public String getV_userId() {
        return v_userId;
    }

    public void setV_userId(String v_userId) {
        this.v_userId = v_userId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
