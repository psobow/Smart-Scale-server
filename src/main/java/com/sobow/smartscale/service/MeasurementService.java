package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.MeasurementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService
{
  @Autowired private MeasurementDao measurementDao;
  
  public List<Measurement> findAll()
  {
    return measurementDao.findAll();
  }
  
  public List<Measurement> findAllById(List<Long> ids)
  {
    return measurementDao.findAllById(ids);
  }
  
  public Measurement findByLocalDateTimeAndUser(LocalDateTime localDateTime, User user)
  {
    return measurementDao.findByLocalDateTimeAndUser(localDateTime,user).orElse(null);
  }
  
  public void deleteAllById(List<Long> ids)
  {
    measurementDao.deleteAll(findAllById(ids));
  }
}
