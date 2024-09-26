package com.projects.socialmedia.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projects.socialmedia.common.models.BaseEntity;
import com.projects.socialmedia.task.domain.Task;
import com.projects.socialmedia.user.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "projects")
public class Project extends BaseEntity {

  @Column(length = 50, nullable = false)
  private String title;

  @JsonManagedReference
  @JsonIgnoreProperties({ "projects" })
  @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<User> users;

  @JsonManagedReference
  @JsonIgnoreProperties({ "project" })
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Task> tasks;
}
