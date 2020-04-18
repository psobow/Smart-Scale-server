package com.sobow.smartscale.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
  
  private int age;
  
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
}
