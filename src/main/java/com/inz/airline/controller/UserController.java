package com.inz.airline.controller;

import com.inz.airline.domain.User;
import com.inz.airline.domain.UserRequest;
import com.inz.airline.exception.ResourceConflictException;
import com.inz.airline.repository.BookingRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.BookingService;
import com.inz.airline.service.JourneyService;
import com.inz.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  JourneyService journeyService;
  @Autowired
  JourneyRepository journeyRepository;
  @Autowired
  BookingRepository bookingRepository;
  @Autowired
  BookingService bookingService;

  @Autowired
  private UserService userService;


  @RequestMapping(method = POST, value = "/signup")
  public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest,
                                   UriComponentsBuilder ucBuilder) {

    User existUser = this.userService.findByUsername(userRequest.getUsername());
    if (existUser != null) {
      throw new ResourceConflictException(userRequest.getId(), "Username already exists");
    }
    User user = this.userService.save(userRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }


  @RequestMapping("/whoami")
//  @PreAuthorize("hasRole('USER')")
  public User user() {

    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }


}
