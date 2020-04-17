package com.sobow.smartscale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto
{
  @Id
  private long id;
  
  private String userName;
  
  private int age;
  
  private String email;
  
  private String sex;
  
  private String password;
  
  // Foreign Key
  private List<Long> measurementIds;

}
