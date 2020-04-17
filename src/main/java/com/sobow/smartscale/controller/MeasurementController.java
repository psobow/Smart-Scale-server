package com.sobow.smartscale.controller;

import com.sobow.smartscale.dto.MeasurementDto;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.MeasurementMapper;
import com.sobow.smartscale.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  
  // POST MAPPING
  @PostMapping
  public void createMeasurement(@Valid @RequestBody final MeasurementDto measurementDto)
  {
    measurementService.save(measurementMapper.mapToMeasurement(measurementDto));
  }
  
  // DELETE MAPPING
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") long id)
  {
    measurementService.deleteById(id);
  }
  
  @DeleteMapping
  public void deleteAllByIds(@RequestBody UserDto userDto)
  {
    measurementService.deleteAllById(userDto.getMeasurementIds());
  }
}
