package com.sobow.smartscale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasurementDto
{
  private LocalDateTime localDateTime;
  
  private double weight;
  
  private double BMI;
  
  // Foreign key
  private long userId;
}
