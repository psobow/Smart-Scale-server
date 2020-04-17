package com.sobow.smartscale.exceptionHandling;

public class UserAlreadyExistException extends RuntimeException
{
  public UserAlreadyExistException(String msg)
  {
    super(msg);
  }
}
