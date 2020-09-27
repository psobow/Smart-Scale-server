package com.sobow.smartscale.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User
{
  @Id
  @GeneratedValue
  @Column(updatable = false, unique = true)
  private long id;
  
  private String userName;
  
  private LocalDate birthDate;
  
  private int height;
  
  private String email;
  
  private String sex;
  
  private String password;
  
  // Foreign Key
  @OneToMany(
      targetEntity = Measurement.class,
      cascade = CascadeType.ALL,
      mappedBy = "user"
  )
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Measurement> measurements;
  
  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    User user = (User) o;
    if (getEmail() != null ? ! getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
    return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
  }
  
  @Override
  public int hashCode()
  {
    int result = getEmail() != null ? getEmail().hashCode() : 0;
    result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
    return result;
  }
}
