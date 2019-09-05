package com.inz.airline.service.impl;

import com.inz.airline.domain.Flight;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(String code) {
        flightRepository.deleteById(code);
    }

    @Override
    public List<Flight> getAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

}
