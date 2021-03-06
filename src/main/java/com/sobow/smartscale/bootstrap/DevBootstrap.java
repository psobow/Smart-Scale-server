package com.sobow.smartscale.bootstrap;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.mapper.UserMapper;
import com.sobow.smartscale.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    user1.setUserName("PatrykSobow");
    user1.setBirthDate(LocalDate.of(1995, 11, 16));
    user1.setHeight(180);
    user1.setEmail("adres@email.pl");
    user1.setSex("Male");
    user1.setPassword("tak");
  
    Measurement measurement1 = new Measurement();
    measurement1.setBMI(20.0);
    measurement1.setWeight(80.0);
    measurement1.setLocalDateTime(LocalDateTime.now().minusDays(5).minusHours(1));
  
    Measurement measurement2 = new Measurement();
    measurement2.setBMI(21.0);
    measurement2.setWeight(75.5);
    measurement2.setLocalDateTime(LocalDateTime.now());
  
  
    Measurement measurement5 = new Measurement();
    measurement5.setBMI(21.0);
    measurement5.setWeight(75.5);
    measurement5.setLocalDateTime(LocalDateTime.now().minusDays(3));
  
    // Set up relation
    List<Measurement> measurements = new ArrayList<>();
    measurements.add(measurement1);
    measurements.add(measurement2);
    measurements.add(measurement5);
  
    measurement1.setUser(user1);
    measurement2.setUser(user1);
    measurement5.setUser(user1);
    user1.setMeasurements(measurements);
  
  
    User user2= new User();
    user2.setUserName("Stasiu Bargiello");
    user2.setBirthDate(LocalDate.of(2005, 11, 16));
    user2.setHeight(180);
    user2.setEmail("new@email.pl");
    user2.setSex("Male");
    user2.setPassword("Witam123");
  
    Measurement measurement3 = new Measurement();
    measurement3.setBMI(15.0);
    measurement3.setWeight(56.5);
    measurement3.setLocalDateTime(LocalDateTime.now());
  
    Measurement measurement4 = new Measurement();
    measurement4.setBMI(16.0);
    measurement4.setWeight(59.5);
    measurement4.setLocalDateTime(LocalDateTime.now());
  
    // Set up relation
    List<Measurement> measurements2 = new ArrayList<>();
    measurements2.add(measurement3);
    measurements2.add(measurement4);
  
    measurement3.setUser(user2);
    measurement4.setUser(user2);
    user2.setMeasurements(measurements2);
    
  
    long user1Id = userService.create(user1).getId();
    long user2Id = userService.create(user2).getId();
    
    
    
    List<User> usersFromDB = userService.findAll();
    System.out.println(usersFromDB.size());
    
    
  }
}
