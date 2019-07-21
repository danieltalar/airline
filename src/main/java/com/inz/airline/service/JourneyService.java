package com.inz.airline.service;

import com.inz.airline.domain.JourneyData;

import java.util.List;

public interface JourneyService {

    List<JourneyData> getJourney(String cityFrom, String cityTo);
}
