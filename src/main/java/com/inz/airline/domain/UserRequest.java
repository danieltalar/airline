package com.inz.airline.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

  private Long id;
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String nationality;
  private LocalDate dateOfBirth;
}
