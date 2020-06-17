package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.MeasurementDao;
import com.sobow.smartscale.domain.dao.UserDao;
import com.sobow.smartscale.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MeasurementService
{
  @Autowired
  private MeasurementDao measurementDao;
  @Autowired
  private UserDao userDao;
  
  public List<Measurement> findAll()
  {
    return measurementDao.findAll();
  }
  
  public List<Measurement> findAllByUser(UserDto userDto)
  {
    User user = userDao.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).orElse(null);
  
    List<Measurement> measurements = new ArrayList<>();
  
    if (user != null)
    {
      measurements.addAll(measurementDao.findAllByUser(user));
    }
  
    return measurements;
  }
  
  public void deleteAllByUser(UserDto userDto)
  {
    User user = userDao.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).orElse(null);
    // break up relation
    user.setMeasurements(new ArrayList<>());
    
    measurementDao.deleteAll(findAllByUser(userDto));
    userDao.save(user);
  }
  
  public void deleteById(Long id)
  {
    Measurement measurement = measurementDao.findById(id).orElse(null);
  
    if (measurement != null)
    {
      User user = userDao.findById(measurement.getUser().getId()).orElse(null);
      user.getMeasurements().remove(measurement); // break relation
      measurementDao.deleteById(id);
      userDao.save(user);
    }
  }
  
  public void save(Measurement measurement)
  {
    User user = measurement.getUser();
    user.getMeasurements().add(measurement);
    User newUser = userDao.save(user);
    log.info("Created new measurement in database");
  }
}
