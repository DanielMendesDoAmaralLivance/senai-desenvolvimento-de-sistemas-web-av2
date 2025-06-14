package com.travels.travels_api.exception;

public abstract class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }
}
