package com.sobow.smartscale.bootstrap;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.UserDao;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.UserMapper;
import com.sobow.smartscale.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
  @Autowired private UserService userService;
  @Autowired private UserMapper userMapper;
  
  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
  {
    initData();
  }
  
  private void initData()
  {
    
    User user1 = new User();
    user1.setUserName("Patryk Sobow");
    user1.setAge(25);
    user1.setEmail("adress@email.pl");
    user1.setSex("Male");
    user1.setPassword("dupa123");
  
    Measurement measurement1 = new Measurement();
    measurement1.setBMI(20.0);
    measurement1.setWeight(80.5);
    measurement1.setLocalDateTime(LocalDateTime.now());
  
    Measurement measurement2 = new Measurement();
    measurement2.setBMI(21.0);
    measurement2.setWeight(75.5);
    measurement2.setLocalDateTime(LocalDateTime.now());
  
    // Set up relation
    List<Measurement> measurements = new ArrayList<>();
    measurements.add(measurement1);
    measurements.add(measurement2);
  
    measurement1.setUser(user1);
    measurement2.setUser(user1);
    user1.setMeasurements(measurements);
  
    long userId = userService.save(user1).getId();
  
    User newUser = userService.findById(userId);
    UserDto newUserDto = userMapper.mapToUserDto(newUser);
    
    User newUser2 = userMapper.mapToUser(newUserDto);
    
    
    List<User> usersFromDB = userService.findAll();
    System.out.println(usersFromDB.size());
    
    
  }
}
