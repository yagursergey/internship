package com.syagur.exception;

import com.syagur.exception.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        CustomErrorResponse error = new CustomErrorResponse();
        error.setTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
