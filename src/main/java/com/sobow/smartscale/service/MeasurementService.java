package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.MeasurementDao;
import com.sobow.smartscale.domain.dao.UserDao;
import com.sobow.smartscale.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MeasurementService
{
  @Autowired private MeasurementDao measurementDao;
  @Autowired private UserDao userDao;
  @Autowired
  private UserService userService;
  
  public List<Measurement> findAll()
  {
    return measurementDao.findAll();
  }
  
  public List<Measurement> findAllByUser(UserDto userDto)
  {
    User user = userService.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    List<Measurement> test = measurementDao.findAllByUser(user);
    return test;
  }
  
  public void deleteAllByUser(UserDto userDto)
  {
    measurementDao.deleteAll(findAllByUser(userDto));
  }
  
  public void deleteById(Long id)
  {
    measurementDao.deleteById(id);
  }
  
  public void save(Measurement measurement)
  {
    User user = measurement.getUser();
    user.getMeasurements().add(measurement);
    User newUser = userDao.save(user);
    log.info("Created new measurement in database");
  }
}
