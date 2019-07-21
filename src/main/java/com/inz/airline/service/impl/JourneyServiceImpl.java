package com.inz.airline.service.impl;

import com.inz.airline.domain.JourneyData;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Override
    public List<JourneyData> getJourney(SearchFlightDto searchFlightDto) {
        AtomicReference<Integer> price= new AtomicReference<>(0);
        List<JourneyData> listOfJourneys = journeyRepository.findListOfJourneys(searchFlightDto.getCityFrom(), searchFlightDto.getCityTo());
        List<JourneyData> listFiltered = new ArrayList<>();
        for (JourneyData journeyData: listOfJourneys){
             AtomicReference<Integer> pr= new AtomicReference<>(0);
             journeyData.getFlights().forEach(flight -> pr.set(pr.get()+flight.getPrice()));
             journeyData.setPrice(pr.get());
//            List<Flight> collect = journeyData.getFlights().stream()
//                    .filter(j -> j.getPrice() > searchFlightDto.getPriceMin())
//                    .filter(j -> j.getPrice() < searchFlightDto.getPriceMax())
//                    .filter(j -> j.getStart().isAfter(searchFlightDto.getDataStartSearch()))
//                    .filter(j -> j.getStart().isBefore(searchFlightDto.getDataEndSearch()))
//                    .collect(Collectors.toList());
//            listFiltered.add(journeyData);


         }

        List<JourneyData> collect = listOfJourneys.stream()
                .filter(j -> j.getPrice() > searchFlightDto.getPriceMin())
                .filter(j -> j.getPrice() < searchFlightDto.getPriceMax())
                .collect(Collectors.toList());

         return collect;
    }
}
