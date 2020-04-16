package com.sobow.smartscale.domain.dao;


import com.sobow.smartscale.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long>
{
  @Override
  List<User> findAll();
  
}
