package com.projects.socialmedia.task.application.dtos;

import com.projects.socialmedia.task.domain.enums.TaskStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TaskUpdateDto {
  private String title;
  private String description;
  private TaskStatusEnum taskStatus;

}
