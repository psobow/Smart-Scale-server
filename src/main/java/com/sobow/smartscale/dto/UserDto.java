package com.sobow.smartscale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto
{
  private String userName;
  
  private int age;
  
  private String email;
  
  private String sex;
  
  private String password;
  
  // Foreign Key
  private List<Long> measurementIds;

}
