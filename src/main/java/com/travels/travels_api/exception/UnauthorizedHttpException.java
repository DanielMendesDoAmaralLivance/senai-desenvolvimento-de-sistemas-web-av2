package com.travels.travels_api.exception;

public class UnauthorizedHttpException extends HttpException {
    public UnauthorizedHttpException(String message) {
        super(message);
    }
}
