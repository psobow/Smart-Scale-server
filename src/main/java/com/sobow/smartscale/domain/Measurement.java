package com.sobow.smartscale.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Measurement
{
  @Id
  @GeneratedValue
  @Column(updatable = false, unique = true)
  private long id;
  
  private LocalDateTime localDateTime;
  
  private double weight;
  
  private double BMI;
  
  // Foreign key
  @ManyToOne
  @JoinColumn(name = "ID_USER")
  private User user;
}
