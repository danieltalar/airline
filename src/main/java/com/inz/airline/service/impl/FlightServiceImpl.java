package com.inz.airline.service.impl;

import com.inz.airline.domain.Flight;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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

    @Override
    public List<Flight> filter(SearchFlightDto searchFlightDto) {
        List<Flight> result = StreamSupport.stream(flightRepository.findAll().spliterator(), false)
                .filter(flight->flight.getPrice() > searchFlightDto.getPriceMin())
                .filter(flight->flight.getPrice() < searchFlightDto.getPriceMax())
                .filter(flight -> flight.getStart().isAfter(searchFlightDto.getDataStartSearch()))
                .filter(flight -> flight.getStart().isBefore(searchFlightDto.getDataEndSearch()))
                .collect(Collectors.toList());
        return result;
    }
}
