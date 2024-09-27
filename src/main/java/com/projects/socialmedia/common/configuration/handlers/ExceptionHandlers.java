package com.projects.socialmedia.common.configuration.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projects.socialmedia.common.exceptions.NotFoundException;
import com.projects.socialmedia.common.models.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> NotFoundException(NotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "Not found",
        ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Set<String> errorsString = new HashSet<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errorsString.add(String.format("field: {%s} error: {%s}", error.getField(), error.getDefaultMessage()));
    });
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Bad Request",
        "Validations Error",
        new ArrayList<>(errorsString));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

  }
}
