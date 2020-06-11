package com.sobow.smartscale.domain.dao;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MeasurementDao extends CrudRepository<Measurement, Long>
{
  @Override
  List<Measurement> findAll();
  
  @Override
  List<Measurement> findAllById(Iterable<Long> IDs);
  
  List<Measurement> findAllByUser(User user);
  
}
