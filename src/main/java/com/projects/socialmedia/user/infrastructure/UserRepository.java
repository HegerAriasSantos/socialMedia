package com.projects.socialmedia.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.socialmedia.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

  User findByUsername(String username);
}
