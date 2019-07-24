package com.inz.airline.service.impl;

import com.inz.airline.domain.Journey;
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

    //TODO - CONVERT TO FORM WHERE JOURNEY IS RETURNED
    @Override
    public List<Journey> getJourney(SearchFlightDto searchFlightDto) {
        AtomicReference<Integer> price= new AtomicReference<>(0);
        List<JourneyData> listOfFlightCodes = journeyRepository.findListOfJourneys(searchFlightDto.getCityFrom(), searchFlightDto.getCityTo());
        List<Journey> listFiltered = new ArrayList<>();
//        for (Journey journey: listOfJourneys){
//             AtomicReference<Integer> pr= new AtomicReference<>(0);
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
