package com.travels.travels_api.dto.api;

public class Response {
    private boolean success = true;
    private String message = "";

    public Response() {}

    public Response(
        boolean success,
        String message
    ) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
