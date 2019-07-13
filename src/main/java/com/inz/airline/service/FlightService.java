package com.inz.airline.service;

import com.inz.airline.domain.Flight;
import java.util.*;

public interface FlightService {

    Flight addFlight(Flight flight);
    void deleteFlight(Long id);
    List<Flight> getAllFlights();
}
