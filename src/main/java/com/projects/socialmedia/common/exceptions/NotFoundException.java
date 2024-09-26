package com.projects.socialmedia.common.exceptions;

public class NotFoundException extends RuntimeException {
  public String message;

  public NotFoundException(String message) {
    super(message);
    this.message = message;
  }

}
