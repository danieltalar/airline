package com.inz.airline.service;

import com.inz.airline.domain.User;
import com.inz.airline.domain.UserRequest;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {


  User findByUsername(String username);

  User save(UserRequest user);
}
