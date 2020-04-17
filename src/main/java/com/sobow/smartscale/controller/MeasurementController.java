package com.sobow.smartscale.controller;

import com.sobow.smartscale.dto.MeasurementDto;
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
  
  @GetMapping
  public List<MeasurementDto> getMeasurementsByIds(@RequestBody List<Long> ids)
  {
    return measurementMapper.mapToMeasurementDtos(measurementService.findAllById(ids));
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
  public void deleteAllForUser(@RequestBody List<Long> ids)
  {
    measurementService.deleteAllById(ids);
  }
}
