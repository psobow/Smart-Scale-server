package com.sobow.smartscale.controller;

import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.UserMapper;
import com.sobow.smartscale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
  
  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable("id") final Long id)
  {
    return userMapper.mapToUserDto(userService.findById(id));
  }
  
  @GetMapping("/{email}/{password}")
  public UserDto getUserByEmailAndPassword(@PathVariable("email") final String email,
                                           @PathVariable("password") final String password)
  {
    return userMapper.mapToUserDto(userService.findByEmailAndPassword(email, password));
  }
  
  // DELETE MAPPINGS
  @DeleteMapping("/{email}/{password}")
  public void deleteUserByEmailAndPassword(@PathVariable("email") final String email,
                                           @PathVariable("password") final String password)
  {
    userService.deleteByEmailAndPassword(email, password);
  }
  
  // PUT MAPPING
  @PutMapping("/{email}/{password}")
  public UserDto updateUser(@PathVariable("email") final String email,
                            @PathVariable("password") final String password,
                            @Valid @NotNull @RequestBody final UserDto userDto)
  {
    return userMapper.mapToUserDto(userService.update(email, password, userDto));
  }
  
  // POST MAPPING
  @PostMapping
  public UserDto createUser(@Valid @NotNull @RequestBody final UserDto userDto)
  {
    return userMapper.mapToUserDto(userService.create(userMapper.mapToUser(userDto)));
  }
  
}
