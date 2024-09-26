package com.projects.socialmedia.common.configuration.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
