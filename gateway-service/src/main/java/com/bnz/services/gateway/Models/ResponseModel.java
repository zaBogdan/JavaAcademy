package com.bnz.services.gateway.Models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel<Model> {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Model> data;
    private String message;

    public ResponseModel(boolean success, List<Model> data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public ResponseModel(boolean success, String message) {
        this.success = success;
        this.data = new ArrayList<>();
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
