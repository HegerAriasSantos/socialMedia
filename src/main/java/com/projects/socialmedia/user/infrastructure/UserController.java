package com.projects.socialmedia.user.infrastructure;

import com.projects.socialmedia.user.application.UserService;
import com.projects.socialmedia.user.application.dtos.UserCreateDto;
import com.projects.socialmedia.user.application.dtos.UserUpdateDto;
import com.projects.socialmedia.user.domain.User;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("users")
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  // asdf:3000?page=10&size=23&sort=asc
  @GetMapping
  public ResponseEntity<Page<User>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "asc") String sort) {
    var sortBy = "firstName";
    Sort sortConfig = sort.equalsIgnoreCase(Sort.Direction.ASC.name())
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

    return new ResponseEntity<>(userService.getAll(PageRequest.of(page - 1, size, sortConfig)), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable String id) {
    return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> create(@Valid @RequestBody UserCreateDto dto) {
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
