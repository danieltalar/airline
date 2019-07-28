package com.inz.airline.service;

import com.inz.airline.domain.User;
import com.inz.airline.domain.UserRequest;

import java.util.List;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  List<User> findAll();

  User save(UserRequest user);
}
