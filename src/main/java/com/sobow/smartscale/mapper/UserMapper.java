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

@Component
public class UserMapper
{
  @Autowired
  private MeasurementService measurementService;
  @Autowired
  private UserService userService;
  
  public User mapToUser(final UserDto userDto)
  {
    List<Measurement> measurements = measurementService.findAllById(userDto.getMeasurementIds());
    long id = userService.findByEmail(userDto.getEmail()).getId();
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
}
