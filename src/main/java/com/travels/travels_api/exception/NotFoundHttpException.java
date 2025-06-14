package com.travels.travels_api.exception;

public class NotFoundHttpException extends HttpException {
    public NotFoundHttpException(String message) {
        super(message);
    }
}
