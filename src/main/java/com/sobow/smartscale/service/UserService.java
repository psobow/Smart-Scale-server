package com.sobow.smartscale.service;

import com.sobow.smartscale.domain.User;
import com.sobow.smartscale.domain.dao.UserDao;
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
    return userDao.findById(id).orElse(null);
  }
  
  public User findByEmail(final String email)
  {
    return userDao.findByEmail(email).orElse(null);
  }
  
  public void deleteById(final long id)
  {
    userDao.deleteById(id);
  }
  
  public User save(final User user)
  {
    return userDao.save(user);
  }
  
}
