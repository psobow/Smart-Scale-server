package com.sobow.smartscale.controller;

import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.UserMapper;
import com.sobow.smartscale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController
{
  private final UserService userService;
  private final UserMapper userMapper;
  
  @GetMapping
  public List<UserDto> getUsers()
  {
    return userMapper.mapToUserDtos(userService.findAll());
  }
  
  @GetMapping("/{id}")
  public UserDto getUser(@PathVariable("id") final long id)
  {
    return userMapper.mapToUserDto(userService.findById(id));
  }
}
