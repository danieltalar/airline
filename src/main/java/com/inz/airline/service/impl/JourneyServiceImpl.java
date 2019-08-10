package com.inz.airline.service.impl;

import com.inz.airline.domain.Flight;
import com.inz.airline.domain.Journey;
import com.inz.airline.domain.JourneyData;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    FlightRepository flightRepository;

    //TODO - CONVERT TO FORM WHERE JOURNEY IS RETURNED
    @Override
    public List<Journey> getJourney(SearchFlightDto searchFlightDto) {

        List<JourneyData> listOfFlightCodes = journeyRepository.findListOfJourneys(searchFlightDto.getCityFrom(), searchFlightDto.getCityTo());
        List<Journey> listFiltered = new ArrayList<>();
        for (JourneyData journeyData: listOfFlightCodes){
            List<Flight> flights = new ArrayList<>();
            journeyData.getFlight_codes().forEach(code -> flights.add(flightRepository.getByCode(code)));

            Journey journey = new Journey();

            journey.setFlights(flights);
            journey.setCityTo(searchFlightDto.getCityTo());
            journey.setCityFrom(searchFlightDto.getCityFrom());
            int sizeBefore = journey.getFlights().size();
            List<Flight> collect = journey.getFlights().stream().filter(flight -> flight.checkHasPlace(searchFlightDto.getTicketType(), searchFlightDto.getCountAdult(), searchFlightDto.getCountChildren())).collect(Collectors.toList());

            int sizeAfter = collect.size();
            if (sizeBefore==sizeAfter) {
                journey.setPrice(journey.Price(searchFlightDto.getTicketType(), searchFlightDto.getCountAdult(), searchFlightDto.getCountChildren()));
                Collections.sort(journey.getFlights());
                journey.setJourney_start(journey.getFlights().get(0).getStart());
                journey.setJourney_finish(journey.getFlights().get(journey.getFlights().size()-1).getEnd());
                listFiltered.add(journey);
            }
            flights.forEach(flight -> {
                if(flight.getStart().isBefore(searchFlightDto.getDataStartSearch()))
                    listFiltered.remove(journey);
            });
        }
        return listFiltered;

    }
}
