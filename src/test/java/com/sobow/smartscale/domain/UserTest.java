package com.sobow.smartscale.domain;

import com.sobow.smartscale.domain.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class UserTest
{
  @Autowired private UserDao userDao;
  
  @Test
  public void shouldPersistUser()
  {
    User user1 = new User();
    user1.setUserName("Patryk Sobow");
    user1.setBirthDate(LocalDate.of(1995, 11, 16));
    user1.setEmail("adress@email.pl");
    user1.setSex("Male");
    user1.setPassword("dupa123");
    
    Measurement measurement = new Measurement();
    measurement.setBMI(20.0);
    measurement.setWeight(80.5);
    measurement.setLocalDateTime(LocalDateTime.now());
    
    // Set up relation
    List<Measurement> measurements = new ArrayList<>();
    measurements.add(measurement);
    
    measurement.setUser(user1);
    user1.setMeasurements(measurements);
    
    userDao.save(user1);
    
    List<User> usersFromDB = userDao.findAll();
    System.out.println(usersFromDB.size());
  }
  
}