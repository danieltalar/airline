package com.inz.airline.service;

import com.inz.airline.domain.JourneyData;
import com.inz.airline.dto.SearchFlightDto;

import java.util.List;

public interface JourneyService {

    List<JourneyData> getJourney(SearchFlightDto searchFlightDto);
}
