package com.inz.airline.service.impl;

import com.inz.airline.domain.JourneyData;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Override
    public List<JourneyData> getJourney(String cityFrom, String cityTo) {
        AtomicReference<Integer> price= new AtomicReference<>(0);
        List<JourneyData> listOfJourneys = journeyRepository.findListOfJourneys(cityFrom, cityTo);
        for (JourneyData journeyData: listOfJourneys){
             AtomicReference<Integer> pr= new AtomicReference<>(0);
             journeyData.getFlights().forEach(flight -> pr.set(pr.get()+flight.getPrice()));
             journeyData.setPrice(pr.get());
         }

         return listOfJourneys;
    }
}
