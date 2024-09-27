package com.projects.socialmedia.user.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projects.socialmedia.common.interfaces.IMapper;
import com.projects.socialmedia.user.application.dtos.UserCreateDto;
import com.projects.socialmedia.user.domain.User;

@Component
public class UserMapper implements IMapper<User, UserCreateDto> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserCreateDto mapTo(User a) {
    int[] nums = { 1, 2, 3, 4, 5, 6 };
    List<Integer> list = new ArrayList<>();

    return modelMapper.map(a, UserCreateDto.class);
  }

  @Override
  public User mapFrom(UserCreateDto b) {
    return modelMapper.map(b, User.class);
  }

}
