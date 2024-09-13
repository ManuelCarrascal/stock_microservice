package com.emazon.stock.infrastructure.configuration.exceptionhandler;

import com.emazon.stock.domain.exception.InvalidPageIndexException;
import com.emazon.stock.domain.exception.InvalidSortByException;
import com.emazon.stock.domain.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.MessageSourceResolvable;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
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

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Error: " + Objects.requireNonNull(ex.getRootCause()).getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSortByException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSortByException(InvalidSortByException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
