package com.travels.travels_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.travels.travels_api.dto.api.Response;
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.exception.NotFoundHttpException;
import com.travels.travels_api.exception.UnauthorizedHttpException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(BadRequestHttpException.class)
    public ResponseEntity<Response> handleBadRequestHttpException(BadRequestHttpException e) {
        return ResponseEntity
            .badRequest()
            .body(new Response(false, e.getMessage()));
    }

    @ExceptionHandler(NotFoundHttpException.class)
    public ResponseEntity<Response> handleNotFoundHttpException(NotFoundHttpException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new Response(false, e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedHttpException.class)
    public ResponseEntity<Response> handleUnauthorizedHttpException(UnauthorizedHttpException e) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(new Response(false, e.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Response> handleAuthorizationDeniedException(AuthorizationDeniedException e) {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(new Response(false, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new Response(false, "Unexpected error"));
    }
}
