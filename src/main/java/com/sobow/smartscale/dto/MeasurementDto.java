package com.sobow.smartscale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasurementDto
{
  @Id
  private long id;
  
  @NotNull
  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime localDateTime;
  
  @Min(value = 0, message = "Weight cannot be negative number")
  private double weight;
  
  @Min(value = 0, message = "BMI cannot be negative number")
  private double BMI;
  
  // Foreign key
  private long userId;
}
