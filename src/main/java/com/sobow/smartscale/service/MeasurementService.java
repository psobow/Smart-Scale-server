package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.MeasurementDao;
import com.sobow.smartscale.domain.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService
{
  @Autowired private MeasurementDao measurementDao;
  @Autowired private UserDao userDao;
  
  public List<Measurement> findAll()
  {
    return measurementDao.findAll();
  }
  
  public List<Measurement> findAllById(List<Long> ids)
  {
    return measurementDao.findAllById(ids);
  }
  
  public void deleteAllById(List<Long> ids)
  {
    measurementDao.deleteAll(findAllById(ids));
  }
  
  public void deleteById(Long id)
  {
    measurementDao.deleteById(id);
  }
  
  public void save(Measurement measurement)
  {
    User user = measurement.getUser();
    user.getMeasurements().add(measurement);
    userDao.save(user);
  }
}
