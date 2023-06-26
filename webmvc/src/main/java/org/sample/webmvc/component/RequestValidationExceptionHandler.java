package org.sample.webmvc.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class RequestValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("request validation error:", ex);
        return super.handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @ExceptionHandler(
            value = { ConstraintViolationException.class }
    )
    protected
    ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.debug("request validation error:", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
