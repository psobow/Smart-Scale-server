package com.sobow.smartscale.mapper;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.service.MeasurementService;
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
  
  public User mapToUser(final UserDto userDto)
  {
    List<Long> measurementIds = new ArrayList<>();
    if (userDto.getMeasurementIds() != null)
    {
      measurementIds.addAll(userDto.getMeasurementIds());
    }
    List<Measurement> measurements = measurementService.findAllByUser(userDto);
    return new User(userDto.getId(),
                    userDto.getUserName(),
                    userDto.getAge(),
                    userDto.getHeight(),
                    userDto.getEmail(),
                    userDto.getSex(),
                    userDto.getPassword(),
                    measurements);
  }
  
  public UserDto mapToUserDto(final User user)
  {
    List<Long> measurementIds = new ArrayList<>();
    user.getMeasurements().forEach(measurement -> measurementIds.add(measurement.getId()));
    
    return new UserDto(user.getId(),
                       user.getUserName(),
                       user.getAge(),
                       user.getHeight(),
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
