package com.hotel.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * GlobalExceptionHandler is a controller advice class that handles exceptions across all controllers in
 * the application. It provides centralized exception handling logic and responses..
 *
 * @author Abdul Basith
 */


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public Map<String, String> handleJsonDeserializationException(
            JsonProcessingException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error Code:", "1001");
        errors.put("Error Message:", "Please check your input");
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleValidationExceptions(
            DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Duplicate Room Error", "Room name already exists");
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public String elementNotFound(NoSuchElementException ex) {

        return "Room Number Not Found !!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> commonError(Exception e) {

        Map<String, String> error = new HashMap<>();
        error.put("Error", "Something went wrong !!");
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, Integer> customerNotFoundException(EntityNotFoundException ex) {

        Map<String, Integer> error = new HashMap<>();
        error.put(ex.getMessage(), ex.getEntityId());
        return error;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String authorizationError() {
        return "Insufficent Priviliges !!" ;
    }

}