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
  
  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Measurement that = (Measurement) o;
    
    if (Double.compare(that.getWeight(), getWeight()) != 0) return false;
    if (Double.compare(that.getBMI(), getBMI()) != 0) return false;
    if (getLocalDateTime() != null ? ! getLocalDateTime().equals(that.getLocalDateTime()) : that.getLocalDateTime() != null)
    { return false; }
    return getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null;
  }
  
  @Override
  public int hashCode()
  {
    int result;
    long temp;
    result = getLocalDateTime() != null ? getLocalDateTime().hashCode() : 0;
    temp = Double.doubleToLongBits(getWeight());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getBMI());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
    return result;
  }
}
