package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.UserDao;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.exceptionHandling.UserAlreadyExistException;
import com.sobow.smartscale.exceptionHandling.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService
{
  @Autowired
  private UserDao userDao;
  
  public List<User> findAll()
  {
    return userDao.findAll();
  }
  
  public User findById(final long id)
  {
    return userDao.findById(id)
                  .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " NotFoundException"));
  }
  
  
  public User findByEmailAndPassword(final String email, final String password)
  {
    return userDao.findByEmailAndPassword(email, password)
                  .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " and password: " + password
                                                               + " NotFoundException"));
  }
  
  public void deleteByEmailAndPassword(final String email, final String password)
  {
    userDao.deleteByEmailAndPassword(email, password);
  }
  
  public User create(final User user)
  {
    // If user email not exist in database save new entry
    if (existsByEmail(user.getEmail()) == false)
    {
      User userFromDb = userDao.save(user);
      log.info("Created new user in database with ID: " + userFromDb.getId());
      return userFromDb;
    }
    else
    {
      throw new UserAlreadyExistException("User with email adress: " + user.getEmail() + " already exists in database. Cannot create entry.");
    }
  }
  
  private boolean existsByEmail(final String email)
  {
    return userDao.existsByEmail(email);
  }
  
  public User update(final String emailOfUserToUpdate, final String passwordOfUserToUpdate, final UserDto userDto)
  {
    // Find that user in database if not exists throw exception
    User userToUpdate = findByEmailAndPassword(emailOfUserToUpdate, passwordOfUserToUpdate);
  
    // Check if new adress email is the same as old one and if not, check if new email isn't already taken
    if (emailOfUserToUpdate.equals(userDto.getEmail()) == false
        && existsByEmail(userDto.getEmail()))
    {
      throw new UserAlreadyExistException("User with email adress: " + userDto.getEmail() + " already exists in database. Cannot update entry.");
    }
    
    userToUpdate.setUserName(userDto.getUserName());
    userToUpdate.setEmail(userDto.getEmail());
    userToUpdate.setPassword(userDto.getPassword());
    userToUpdate.setSex(userDto.getSex());
    userToUpdate.setAge(userDto.getAge());
    
    // Update info in database
    return userDao.save(userToUpdate);
  }
}
