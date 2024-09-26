package com.projects.socialmedia.user.application.dtos;

import java.util.List;

import com.projects.socialmedia.task.application.dtos.TaskCreateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
  private String firstName;
  private String lastName;
  private String password;
  private String username;
  private List<TaskCreateDto> tasks;
}
