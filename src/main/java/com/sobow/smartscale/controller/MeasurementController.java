package com.sobow.smartscale.controller;

import com.sobow.smartscale.dto.MeasurementDto;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.mapper.MeasurementMapper;
import com.sobow.smartscale.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/measurement")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MeasurementController
{
  private final MeasurementService measurementService;
  private final MeasurementMapper measurementMapper;
  
  
  // POST MAPPING
  @PostMapping
  public List<MeasurementDto> getMeasurementsByUser(@RequestBody @NotNull UserDto userDto)
  {
    return measurementMapper.mapToMeasurementDtos(measurementService.findAllByUser(userDto));
  }
  
  @PostMapping("/create")
  public void createMeasurement(@Valid @RequestBody @NotNull final MeasurementDto measurementDto)
  {
    measurementService.save(measurementMapper.mapToMeasurement(measurementDto));
  }
  
  // DELETE MAPPING
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") @NotNull Long id)
  {
    measurementService.deleteById(id);
  }
  
  @DeleteMapping
  public void deleteAllByUser(@RequestBody @NotNull UserDto userDto)
  {
    measurementService.deleteAllByUser(userDto);
  }
}
