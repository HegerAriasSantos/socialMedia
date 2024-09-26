package com.projects.socialmedia.task.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.socialmedia.task.domain.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
  @Query("SELECT t FROM Task t Join t.user u WHERE u.id = ?1")
  List<Task> findAllWithUser();
}
