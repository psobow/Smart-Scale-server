package com.sobow.smartscale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto
{
  @Id
  private long id;
  
  @NotEmpty(message = "User name cannot be null or empty")
  @Pattern(regexp = "[A-Za-z0-9]{3,20}", message = "User name can contain only letters and numbers and has to be from 3 to 20 char length")
  private String userName;
  
  @Min(value = 1, message = "Age cannot be zero or negative number")
  private int age;
  
  @Min(value = 1, message = "Height cannot be zero or negative number")
  private int height;
  
  @NotEmpty(message = "Email cannot be null or empty")
  @Pattern(regexp = "^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)",
           message = "Invalid email address")
  private String email;
  
  @NotEmpty(message = "Sex cannot be null or empty")
  @Pattern(regexp = "Male|Female", message = "Invalid sex. Should be Male or Female")
  private String sex;
  
  @NotEmpty(message = "Password cannot be null or empty")
  @Pattern(regexp = "[^\\s]{3,20}", message = "Password cannot contain whitespaces and has to be from 3 to 20 char length")
  private String password;
  
  // Foreign Key
  private List<Long> measurementIds;
  
}
