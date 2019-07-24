package com.inz.airline.service;

import com.inz.airline.domain.Journey;
import com.inz.airline.dto.SearchFlightDto;

import java.util.List;

public interface JourneyService {

    List<Journey> getJourney(SearchFlightDto searchFlightDto);
}
