package com.sobow.smartscale.mapper;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.service.MeasurementService;
import com.sobow.smartscale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper
{
  @Autowired
  private MeasurementService measurementService;
  @Autowired
  private UserService userService;
  
  public User mapToUser(final UserDto userDto)
  {
    long id = -1;
    if (userService.existsByEmail(userDto.getEmail()))
    {
      id = userService.findByEmail(userDto.getEmail()).getId();
    }
    List<Measurement> measurements = measurementService.findAllById(userDto.getMeasurementIds());
    return new User(id,
                    userDto.getUserName(),
                    userDto.getAge(),
                    userDto.getEmail(),
                    userDto.getSex(),
                    userDto.getPassword(),
                    measurements);
  }
  
  public UserDto mapToUserDto(final User user)
  {
    List<Long> measurementIds = new ArrayList<>();
    user.getMeasurements().forEach(measurement -> measurementIds.add(measurement.getId()));
    
    return new UserDto(user.getUserName(),
                       user.getAge(),
                       user.getEmail(),
                       user.getSex(),
                       user.getPassword(),
                       measurementIds);
  }
  
  public List<User> mapToUsers(List<UserDto> userDtos)
  {
    return userDtos.stream().map(this::mapToUser).collect(Collectors.toList());
  }
  
  public List<UserDto> mapToUserDtos(List<User> users)
  {
    return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
  }
}
