package com.inz.airline.controller;


import com.inz.airline.domain.Flight;
import com.inz.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/all")
    ResponseEntity<List<Flight>> getFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.ACCEPTED);
    }

    @PostMapping("")
    ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteFlight(@PathVariable("id") Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>("DELETED", HttpStatus.CREATED);
    }





}
