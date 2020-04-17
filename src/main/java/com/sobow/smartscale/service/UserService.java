package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.UserDao;
import com.sobow.smartscale.dto.UserDto;
import com.sobow.smartscale.exceptionHandling.UserAlreadyExistException;
import com.sobow.smartscale.exceptionHandling.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
  
  public User findByEmail(final String email)
  {
    return userDao.findByEmail(email)
                  .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " NotFoundException"));
  }
  
  public User findByEmailAndPassword(final String email, final String password)
  {
    return userDao.findByEmailAndPassword(email, password)
                  .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " and password: " + password
                                                               + " NotFoundException"));
  }
  
  public void deleteById(final long id)
  {
    userDao.deleteById(id);
  }
  
  public void deleteByEmail(final String email)
  {
    userDao.deleteByEmail(email);
  }
  
  public User save(final User user)
  {
    return userDao.save(user);
  }
  
  public boolean existsByEmail(final String email)
  {
    return userDao.existsByEmail(email);
  }
  
  public User update(final String emailOfUserToUpdate, final UserDto userDto)
  {
    // Find that user in database if not exists throw exception
    User userToUpdate = findByEmail(emailOfUserToUpdate);
    
    // Check if new adress email is the same as old one and if not check if new email isnt already taken
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
    return save(userToUpdate);
  }
}
