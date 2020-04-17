package com.sobow.smartscale.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice
{
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseBody ErrorInfo
  handleUserNotFound(HttpServletRequest req, Exception ex)
  {
    return new ErrorInfo(req.getRequestURI(), ex);
  }
}
