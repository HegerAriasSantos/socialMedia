package com.projects.socialmedia.common.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  private int status;
  private String error;
  private String message;
  private List<String> validations = new ArrayList<>();

  public ErrorResponse(int status, String error, String message) {
    this.status = status;
    this.error = error;
    this.message = message;
    validations = new ArrayList<>();
  }

}
