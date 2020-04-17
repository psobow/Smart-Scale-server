package com.sobow.smartscale.controller;

import com.sobow.smartscale.dto.MeasurementDto;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.MeasurementMapper;
import com.sobow.smartscale.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/measurement")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MeasurementController
{
  private final MeasurementService measurementService;
  private final MeasurementMapper measurementMapper;
  
  // GET MAPPINGS
  @GetMapping
  public List<MeasurementDto> getMeasurements()
  {
    return measurementMapper.mapToMeasurementDtos(measurementService.findAll());
  }
  
  // DELETE MAPPING
  @DeleteMapping
  public void deleteByIds(@RequestBody UserDto userDto)
  {
    measurementService.deleteAllById(userDto.getMeasurementIds());
  }
}
