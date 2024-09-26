package com.projects.socialmedia.user.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserUpdateDto {
  private String firstName;
  private String lastName;
  private String password;
  private String username;
}
