package com.projects.socialmedia.user.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projects.socialmedia.common.models.BaseEntity;
import com.projects.socialmedia.project.domain.Project;
import com.projects.socialmedia.task.domain.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "users")
public class User extends BaseEntity {

  @Column(length = 50, nullable = false, name = "first_name")
  private String fistName;

  @Column(length = 50, nullable = false, name = "last_name")
  private String lastName;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 50, unique = true, nullable = false)
  private String username;

  @JsonManagedReference
  @JsonIgnoreProperties({ "user" })
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Task> tasks;

  @JsonManagedReference
  @JsonIgnoreProperties({ "users" })
  @JoinTable(name = "users_projects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Project> projects;

}
