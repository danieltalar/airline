package com.inz.airline.controller;

import com.inz.airline.domain.JourneyData;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @PostMapping("/journey")
    ResponseEntity<List<JourneyData>> getJourney(@RequestBody SearchFlightDto searchFlightDto){
        return new ResponseEntity<>(journeyService.getJourney(searchFlightDto), HttpStatus.ACCEPTED);
    }
}
