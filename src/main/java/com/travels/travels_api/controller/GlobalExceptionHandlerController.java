package com.travels.travels_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.travels.travels_api.dto.api.Response;
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.exception.NotFoundHttpException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(BadRequestHttpException.class)
    public ResponseEntity<Response<Object>> handleBadRequestHttpException(BadRequestHttpException e) {
        return ResponseEntity
            .badRequest()
            .body(new Response<>(e.getMessage(), null));
    }

    @ExceptionHandler(NotFoundHttpException.class)
    public ResponseEntity<Response<Object>> handleNotFoundHttpException(NotFoundHttpException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new Response<>(e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new Response<>("Unexpected error", null));
    }
}
