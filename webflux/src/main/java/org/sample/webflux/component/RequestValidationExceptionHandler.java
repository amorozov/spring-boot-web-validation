package org.sample.webflux.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import jakarta.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class RequestValidationExceptionHandler {

    @ExceptionHandler(
            value = { WebExchangeBindException.class }
    )
    protected Mono<ResponseEntity<String>> handleWebExchangeBindException(
            WebExchangeBindException ex
    ) {
        log.debug("request validation error:", ex);
        return Mono.just(ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @ExceptionHandler(
            value = { ConstraintViolationException.class }
    )
    protected Mono<ResponseEntity<String>> handleConstraintViolation(
            ConstraintViolationException ex
    ) {
        log.debug("request validation error:", ex);
        return Mono.just(ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
