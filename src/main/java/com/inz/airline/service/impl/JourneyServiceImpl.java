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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    FlightRepository flightRepository;


    @Override
    public List<Journey> getJourney(SearchFlightDto searchFlightDto) {

        List<JourneyData> listOfFlightCodes = journeyRepository.findListOfJourneys(searchFlightDto.getCityFrom(), searchFlightDto.getCityTo());;
        List<Journey> listFiltered = new ArrayList<>();
        for (JourneyData journeyData: listOfFlightCodes){
            List<Flight> flights = new ArrayList<>();
            journeyData.getFlight_codes().forEach(code -> flights.add(flightRepository.getByCode(code)));
            Journey journey = new Journey();

            journey.setFlights(flights);
            journey.setCityTo(searchFlightDto.getCityTo());
            journey.setCityFromJourney(searchFlightDto.getCityFrom());
            int sizeBefore = journey.getFlights().size();
            System.out.println(searchFlightDto);
            List<Flight> collect = journey.getFlights().stream().filter(flight -> flight.checkHasPlace(searchFlightDto.getTicketType(), searchFlightDto.getCountChildren(), searchFlightDto.getCountAdult())).collect(Collectors.toList());

            int sizeAfter = collect.size();
            if (sizeBefore==sizeAfter) {
                Collections.sort(journey.getFlights());
                journey.setJourney_start(journey.getFlights().get(0).getStart());
                journey.setJourney_finish(journey.getFlights().get(journey.getFlights().size()-1).getEnd());
                AtomicReference<Double> price = new AtomicReference<>((double) 0);
                AtomicReference<Double> priceForChildren = new AtomicReference<>((double) 0);
                AtomicReference<Double> priceForAdults = new AtomicReference<>((double) 0);
                 journey.getFlights().forEach(flight -> priceForChildren.updateAndGet(v -> new Double((double) (v + flight.takePrice(searchFlightDto.getTicketType(), searchFlightDto.getCountChildren(), 0)))));
                 journey.getFlights().forEach(flight -> priceForAdults.updateAndGet(v -> new Double((double) (v + flight.takePrice(searchFlightDto.getTicketType(), 0, searchFlightDto.getCountAdult())))));
                 journey.setPriceForChildren(priceForChildren.get());
                 journey.setPriceForAdults(priceForAdults.get());
                journey.setPriceJourney(priceForChildren.get()+priceForAdults.get());
                journey.setDuration(ChronoUnit.MINUTES.between(journey.getJourney_start(), journey.getJourney_finish()));

                journey.getFlights().forEach(flight -> {
                    long minutes=0;
                     minutes= ChronoUnit.MINUTES.between(flight.getStart(), flight.getEnd());
                     flight.setDuration(minutes);
                });
                listFiltered.add(journey);
                if(journey.getJourney_start().isBefore(searchFlightDto.getDataStartSearch())){
                    listFiltered.remove(journey);
                }
            }

        }
        return listFiltered;

    }

    @Override
    public Journey addJourney(Journey journey) {
        return journeyRepository.save(journey);
    }
}
