package com.sobow.smartscale.controller;

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
  
  // GET MAPPINGS
  @GetMapping
  public List<UserDto> getUsers()
  {
    return userMapper.mapToUserDtos(userService.findAll());
  }
  
  @GetMapping("/{email}/{password}")
  public UserDto getUserByEmail(@PathVariable("email") final String email, @PathVariable("password") final String password)
  {
    return userMapper.mapToUserDto(userService.findByEmailAndPassword(email, password));
  }
  
  // DELETE MAPPINGS
  @DeleteMapping("/{email}")
  public void deleteUserByEmail(@PathVariable("email") final String email)
  {
    userService.deleteByEmail(email);
  }
  
  // PUT MAPPING
  @PutMapping("/{email}")
  public UserDto updateUser(@PathVariable("email") final String email, @RequestBody final UserDto userDto)
  {
    return userMapper.mapToUserDto(userService.update(email,userDto));
  }
  
  // POST MAPPING
  @PostMapping
  public void createUser(@RequestBody final UserDto userDto)
  {
    userService.save(userMapper.mapToUser(userDto));
  }
  
}
