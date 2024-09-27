package com.projects.socialmedia.user.application.dtos;

import java.util.List;

import com.projects.socialmedia.task.application.dtos.TaskCreateDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
  @NotBlank(message = "is required")
  private String firstName;

  @NotBlank(message = "is required")
  private String lastName;

  @NotBlank(message = "is required")
  private String password;

  @NotBlank(message = "is required")
  private String username;

  private List<TaskCreateDto> tasks;
}
