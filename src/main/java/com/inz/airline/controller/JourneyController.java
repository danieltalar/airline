package com.inz.airline.controller;

import com.inz.airline.domain.Booking;
import com.inz.airline.domain.Journey;
import com.inz.airline.domain.User;
import com.inz.airline.dto.BookingDto;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.BookingRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.BookingService;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class JourneyController {

    @Autowired
    JourneyService journeyService;
    @Autowired
    JourneyRepository journeyRepository;
     @Autowired
     BookingRepository bookingRepository;
    @Autowired
    BookingService bookingService;

    @PostMapping("/journey")
    ResponseEntity<List<Journey>> getJourney(@RequestBody SearchFlightDto searchFlightDto){
        return new ResponseEntity<>(journeyService.getJourney(searchFlightDto), HttpStatus.ACCEPTED);
    }



    @GetMapping("getAllJourney")
    ResponseEntity<List<Journey>> getjourney(){
        return new ResponseEntity<>((List<Journey>) journeyRepository.findAll(), HttpStatus.ACCEPTED);

    }
    @GetMapping("getAllBookings")
    ResponseEntity<List<Booking>> getBookings(){
        return new ResponseEntity<>((List<Booking>) bookingRepository.findAll(), HttpStatus.ACCEPTED);

    }
    @GetMapping("reservations")
    ResponseEntity<List<Booking>> getMyReservations(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(bookingService.getMyReservations(principal.getUsername()), HttpStatus.ACCEPTED);

    }

    @PostMapping("/booking")
    ResponseEntity<Booking> bookFlights(@RequestBody BookingDto bookingDto){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal);
        bookingDto.setOwner(principal.getUsername());

        return new ResponseEntity<>(bookingService.addBooking(bookingDto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/cancelBooking")
    ResponseEntity<String> bookFlights(@RequestBody Long id){

        bookingService.cancelBooking(id);

        return new ResponseEntity<>("DELETED", HttpStatus.ACCEPTED);
    }
    @PostMapping("/addJourney")
    ResponseEntity<Journey> bookFlights(@RequestBody Journey journey){

        return new ResponseEntity<>(journeyService.addJourney(journey), HttpStatus.CREATED);
    }
}
