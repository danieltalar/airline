package com.inz.airline.service;

import java.util.List;

public interface AuthorityService {
  List<com.inz.airline.model.Authority> findById(Long id);

  List<com.inz.airline.model.Authority> findByname(String name);

}
