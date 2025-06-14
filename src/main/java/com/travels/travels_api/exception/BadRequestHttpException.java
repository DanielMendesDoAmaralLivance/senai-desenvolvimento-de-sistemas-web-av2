package com.travels.travels_api.exception;

public class BadRequestHttpException extends HttpException {
    public BadRequestHttpException(String message) {
        super(message);
    }
}
