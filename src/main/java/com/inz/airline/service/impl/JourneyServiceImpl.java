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
//            AtomicReference<Integer> price= new AtomicReference<>(0);
//            flights.forEach(flight -> price+= flight.ge);
            Journey journey = new Journey();

            journey.setFlights(flights);
            journey.setCityTo(searchFlightDto.getCityTo());
            journey.setCityFrom(searchFlightDto.getCityFrom());
            int sizeBefore = journey.getFlights().size();
            List<Flight> collect = journey.getFlights().stream().filter(flight -> flight.checkHasPlace(searchFlightDto.getTicketType(), searchFlightDto.getCountAdult(), searchFlightDto.getCountChildren())).collect(Collectors.toList());
            int sizeAfter = collect.size();
            if (sizeBefore==sizeAfter) {

                listFiltered.add(journey);
            }

            int suma = 0;
            for (Journey journey1 : listFiltered) {
                journey1.getPrice();
            }
//            listFiltered.forEach( journey1 -> journey1.getFlights().forEach(flight -> flight.getTickets().));

        }

            // AtomicReference<Integer> pr= new AtomicReference<>(0);
////            List<Flight> collect = journeyData.getFlights().stream()
////                    .filter(j -> j.getPrice() > searchFlightDto.getPriceMin())
////                    .filter(j -> j.getPrice() < searchFlightDto.getPriceMax())
////                    .filter(j -> j.getStart().isAfter(searchFlightDto.getDataStartSearch()))
////                    .filter(j -> j.getStart().isBefore(searchFlightDto.getDataEndSearch()))
////                    .collect(Collectors.toList());
////            listFiltered.add(journeyData);
//
//
//         }
//
//        List<JourneyData> collect = listOfJourneys.stream()
//                .collect(Collectors.toList());
//
//         return collect;
        return listFiltered;

    }
}
