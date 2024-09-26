package com.projects.socialmedia.task.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.socialmedia.common.exceptions.NotFoundException;
import com.projects.socialmedia.task.application.dtos.TaskCreateDto;
import com.projects.socialmedia.task.application.dtos.TaskUpdateDto;
import com.projects.socialmedia.task.domain.Task;
import com.projects.socialmedia.task.domain.enums.TaskStatusEnum;
import com.projects.socialmedia.task.infrastructure.TaskRepository;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> getAll() {
    return taskRepository.findAll();
  }

  public Task getById(String id) {
    return this.taskRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Task with id {%s} not found", id)));
  }

  public Task create(TaskCreateDto dto) {
    var task = Task.builder()
        .title(dto.title)
        .description(dto.description)
        .taskStatus(TaskStatusEnum.TODO)
        .build();
    return this.taskRepository.save(task);
  }

  public Task update(TaskUpdateDto dto, String id) {
    var taskFounded = this.getById(id);
    taskFounded.setTitle(dto.getTitle());
    taskFounded.setDescription(dto.getDescription());
    taskFounded.setTaskStatus(dto.getTaskStatus());
    return this.taskRepository.save(taskFounded);
  }

  public boolean deleteById(String id) {
    var taskFounded = this.getById(id);
    this.taskRepository.delete(taskFounded);
    return true;
  }

}
