package com.projects.socialmedia.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projects.socialmedia.common.models.BaseEntity;
import com.projects.socialmedia.project.domain.Project;
import com.projects.socialmedia.task.domain.enums.TaskStatusEnum;
import com.projects.socialmedia.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

  @Column(nullable = false)
  private String title;

  @Column(length = 200)
  private String description;

  @Column(name = "task_status")
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private TaskStatusEnum taskStatus = TaskStatusEnum.TODO;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @JsonIgnoreProperties({ "tasks" })
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id")
  @JsonIgnoreProperties({ "tasks" })
  private Project project;
}
