package com.inz.airline.controller;


import com.inz.airline.domain.Flight;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.service.FlightService;
import com.inz.airline.service.JourneyService;
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

    @Autowired
    JourneyService journeyService;

    @GetMapping("/all")
    ResponseEntity<List<Flight>> getFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.ACCEPTED);
    }
    @PostMapping("/criteria")
    ResponseEntity<List<Flight>> getFlights(@RequestBody SearchFlightDto searchFlightDto){
        return new ResponseEntity<>(flightService.filter(searchFlightDto), HttpStatus.ACCEPTED);
    }


    @PostMapping("")
    ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.CREATED);
    }

    @DeleteMapping("/{code}")
    ResponseEntity<String> deleteFlight(@PathVariable("code") String code){
        flightService.deleteFlight(code);
        return new ResponseEntity<>("DELETED", HttpStatus.CREATED);
    }





}
