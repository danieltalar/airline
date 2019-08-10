package com.inz.airline.controller;

import com.inz.airline.domain.Journey;
import com.inz.airline.dto.BookingDto;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.service.BookingService;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class JourneyController {

    @Autowired
    JourneyService journeyService;
    @Autowired
    BookingService bookingService;

    @PostMapping("/journey")
    ResponseEntity<List<Journey>> getJourney(@RequestBody SearchFlightDto searchFlightDto){
        return new ResponseEntity<>(journeyService.getJourney(searchFlightDto), HttpStatus.ACCEPTED);
    }
    @PostMapping("/booking")
    ResponseEntity<String> bookFlights(@RequestBody BookingDto bookingDto){
        System.out.println(bookingDto.getTicketType());
        bookingService.addBooking(bookingDto);
        return new ResponseEntity<>("BOOKED", HttpStatus.CREATED);
    }
    @PostMapping("/addJourney")
    ResponseEntity<Journey> bookFlights(@RequestBody Journey journey){

        return new ResponseEntity<>(journeyService.addJourney(journey), HttpStatus.CREATED);
    }
}
