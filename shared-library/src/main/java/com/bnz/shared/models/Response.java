package com.bnz.shared.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Response<Model> {
    private boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Model data;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public Response(boolean success, String message, Model data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Model getData() {
        return data;
    }

    public void setData(Model data) {
        this.data = data;
    }
}
