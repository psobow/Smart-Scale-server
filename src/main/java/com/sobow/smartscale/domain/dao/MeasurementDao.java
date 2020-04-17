package com.sobow.smartscale.domain.dao;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MeasurementDao extends CrudRepository<Measurement, Long>
{
  @Override
  List<Measurement> findAll();
  
  @Override
  List<Measurement> findAllById(Iterable<Long> IDs);
  
  Optional<Measurement> findByLocalDateTimeAndUser(LocalDateTime localDateTime, User user);
  
}
