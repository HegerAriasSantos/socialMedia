package com.projects.socialmedia.user.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.socialmedia.common.exceptions.NotFoundException;
import com.projects.socialmedia.task.domain.Task;
import com.projects.socialmedia.task.infrastructure.TaskRepository;
import com.projects.socialmedia.user.application.dtos.UserCreateDto;
import com.projects.socialmedia.user.application.dtos.UserUpdateDto;
import com.projects.socialmedia.user.domain.User;
import com.projects.socialmedia.user.infrastructure.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(String id) {
    return this.userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("User with id {%s} not found", id)));
  }

  public User create(UserCreateDto dto) {
    var foundedUser = this.userRepository.findByUsername(dto.getUsername());
    if (foundedUser != null) {
      // TODO: create a new exception to handle this error
      throw new NotFoundException(String.format("User with username {%s} already exists", dto.getUsername()));
    }

    var userToCreate = User
        .builder()
        .fistName(dto.getFirstName())
        .lastName(dto.getLastName())
        .password(dto.getPassword())
        .username(dto.getUsername())
        .build();
    var createdUser = this.userRepository.save(userToCreate);

    List<Task> tasksList = null;
    if (dto.getTasks().size() > 0) {
      tasksList = dto.getTasks().stream()
          .map((task) -> Task.builder()
              .title(task.title)
              .description(task.description)
              .user(createdUser)
              .build())
          .toList();
      taskRepository.saveAll(tasksList);
    }

    return this.getById(createdUser.getId());
  }

  public User update(UserUpdateDto dto, String id) {
    var userFounded = this.getById(id);
    if (dto.getFirstName() != null)
      userFounded.setFistName(dto.getFirstName());
    if (dto.getLastName() != null)
      userFounded.setLastName(dto.getLastName());
    if (dto.getPassword() != null)
      userFounded.setPassword(dto.getPassword());
    if (dto.getUsername() != null)
      userFounded.setUsername(dto.getUsername());

    return this.userRepository.save(userFounded);
  }

  public boolean deleteById(String id) {
    var userFounded = this.getById(id);
    taskRepository.deleteAll(userFounded.getTasks());
    this.userRepository.delete(userFounded);
    return true;
  }

}
