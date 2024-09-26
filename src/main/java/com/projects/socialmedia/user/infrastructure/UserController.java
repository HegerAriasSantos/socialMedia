package com.projects.socialmedia.user.infrastructure;

import java.util.List;

import com.projects.socialmedia.user.application.UserService;
import com.projects.socialmedia.user.application.dtos.UserCreateDto;
import com.projects.socialmedia.user.application.dtos.UserUpdateDto;
import com.projects.socialmedia.user.domain.User;

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

@RequestMapping("users")
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable String id) {
    return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody UserCreateDto dto) {
    return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> update(@RequestBody UserUpdateDto entity,
      @PathVariable String id) {

    return new ResponseEntity<>(userService.update(entity, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable String id) {
    return new ResponseEntity<>(userService.deleteById(id), HttpStatus.NO_CONTENT);
  }

}
