package com.projects.socialmedia.task.infrastructure;

import java.util.List;

import com.projects.socialmedia.task.application.TaskService;
import com.projects.socialmedia.task.application.dtos.TaskCreateDto;
import com.projects.socialmedia.task.application.dtos.TaskUpdateDto;
import com.projects.socialmedia.task.domain.Task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// GET:  http://localhost:3000/tasks

@RequestMapping("tasks")
@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Operation(description = "Get Examples Service", responses = {
      @ApiResponse(responseCode = "400", ref = "badRequest"),
      @ApiResponse(responseCode = "500", ref = "internalServerError"),
      @ApiResponse(responseCode = "200", ref = "successfulResponse")
  })
  @GetMapping
  public ResponseEntity<List<Task>> getTasks() {
    return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getById(@PathVariable String id) {
    return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto dto) {
    return new ResponseEntity<>(taskService.create(dto), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Task> updateTask(@RequestBody TaskUpdateDto entity,
      @PathVariable String id) {

    return new ResponseEntity<>(taskService.update(entity, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteTask(@PathVariable String id) {
    return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.NO_CONTENT);
  }

}
