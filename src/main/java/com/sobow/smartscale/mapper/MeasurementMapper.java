package com.sobow.smartscale.mapper;

import com.sobow.smartscale.domain.Measurement;
import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.dto.MeasurementDto;
import com.sobow.smartscale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeasurementMapper
{
  @Autowired
  private UserService userService;
  
  public Measurement mapToMeasurement(MeasurementDto measurementDto)
  {
    User userFromDatabase = userService.findById(measurementDto.getUserId());
    return new Measurement(measurementDto.getId(),
                           measurementDto.getLocalDateTime(),
                           measurementDto.getWeight(),
                           measurementDto.getBMI(),
                           userFromDatabase);
  }
  
  public MeasurementDto mapToMeasurementDto(Measurement measurement)
  {
    return new MeasurementDto(measurement.getId(),
                              measurement.getLocalDateTime(),
                              measurement.getWeight(),
                              measurement.getBMI(),
                              measurement.getUser().getId());
  }
  
  public List<Measurement> mapToMeasurements(List<MeasurementDto> measurementDtos)
  {
    return measurementDtos.stream().map(this::mapToMeasurement).collect(Collectors.toList());
  }
  
  public List<MeasurementDto> mapToMeasurementDtos(List<Measurement> measurements)
  {
    return measurements.stream().map(this::mapToMeasurementDto).collect(Collectors.toList());
  }
}
