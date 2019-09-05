package com.inz.airline.service.impl;

import com.inz.airline.repository.AuthorityRepository;
import com.inz.airline.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<com.inz.airline.model.Authority> findById(Long id) {
    // TODO Auto-generated method stub

    com.inz.airline.model.Authority auth = this.authorityRepository.findById(id).get();
    List<com.inz.airline.model.Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

  @Override
  public List<com.inz.airline.model.Authority> findByname(String name) {
    // TODO Auto-generated method stub
    com.inz.airline.model.Authority auth = this.authorityRepository.findByName(name);
    List<com.inz.airline.model.Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

}
