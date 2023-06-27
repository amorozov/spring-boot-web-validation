package org.sample.webflux.component

import io.klogging.NoCoLogging
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException

@ControllerAdvice
class RequestValidationExceptionHandler : NoCoLogging {
    @ExceptionHandler(value = [WebExchangeBindException::class])
    fun handleWebExchangeBindException(ex: WebExchangeBindException): ResponseEntity<String> {
        logger.debug("web exchange bind error:", ex)
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<String> {
        logger.debug("constraint violation error:", ex)
        return ResponseEntity.badRequest().body(ex.message)

    }
}
