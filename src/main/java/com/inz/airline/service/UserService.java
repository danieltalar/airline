package com.inz.airline.service;

import com.inz.airline.domain.User;
import com.inz.airline.domain.UserRequest;


public interface UserService {


  User findByUsername(String username);

  User save(UserRequest user);
}
