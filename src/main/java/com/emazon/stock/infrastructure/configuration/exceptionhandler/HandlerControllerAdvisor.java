package com.emazon.stock.infrastructure.configuration.exceptionhandler;

import com.emazon.stock.domain.exception.InvalidPageIndexException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.MessageSourceResolvable;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errors = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", errors));

    }

    @ExceptionHandler(InvalidPageIndexException.class)
    public ResponseEntity<String> handleInvalidPageIndexException(InvalidPageIndexException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
