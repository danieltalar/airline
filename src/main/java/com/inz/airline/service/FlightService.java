package com.inz.airline.service;

import com.inz.airline.domain.Flight;
import com.inz.airline.dto.SearchFlightDto;

import java.util.List;

public interface FlightService {

    Flight addFlight(Flight flight);
    void deleteFlight(String code);
    List<Flight> getAllFlights();
    List<Flight> filter(SearchFlightDto searchFlightDto);
}
