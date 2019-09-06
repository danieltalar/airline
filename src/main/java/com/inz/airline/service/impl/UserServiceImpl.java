package com.inz.airline.service.impl;

import com.inz.airline.domain.User;
import com.inz.airline.domain.UserRequest;
import com.inz.airline.repository.UserRepository;
import com.inz.airline.service.AuthorityService;
import com.inz.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan.jin on 2016-10-15.
 */

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthorityService authService;


  @Override
  // @PreAuthorize("hasRole('USER')")
  public User findByUsername(String username) throws UsernameNotFoundException {
    User u = userRepository.findByUsername(username);
    return u;
  }



  @Override
  public User save(UserRequest userRequest) {
    User user = new User();
    user.setUsername(userRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setFirst_name(userRequest.getFirstname());
    user.setLast_name(userRequest.getLastname());
    user.setNationality(userRequest.getNationality());
    user.setDateOfBirth(userRequest.getDateOfBirth());

    List<com.inz.airline.model.Authority> auth = authService.findByname("ROLE_USER");
    user.setAuthorities(auth);
    this.userRepository.save(user);
    return user;
  }

}
