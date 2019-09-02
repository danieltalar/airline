package com.inz.airline.service;

import com.inz.airline.domain.City;
import com.inz.airline.domain.Flight;
import com.inz.airline.domain.Journey;
import com.inz.airline.domain.JourneyData;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.CityRepository;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.JourneyService;
import com.inz.airline.service.impl.JourneyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class JourneyServiceTests {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public JourneyService journeyService() {
            return new JourneyServiceImpl();
        }
    }

    @Autowired
    private JourneyService journeyService;

    @MockBean
    private JourneyRepository journeyRepository;

    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private FlightRepository flightRepository;

    @Before
    public void setUp() {

        LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 1, 20, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2019, Month.MARCH, 2, 2, 1);
        LocalDateTime dateTime3 = LocalDateTime.of(2019, Month.MARCH, 2, 12, 1);
        City LosAngeles = new City("Los Angeles","United States of America");
        City NewYork = new City("New York","United States of America");
        City Istanbul = new City("Istanbul","Turkey");

        Mockito.when(cityRepository.getByName("Los Angeles"))
                .thenReturn(LosAngeles);
        Mockito.when(cityRepository.getByName("New York"))
                .thenReturn(NewYork);
        Mockito.when(cityRepository.getByName("Istanbul"))
                .thenReturn(Istanbul);
        JourneyData journeyData = new JourneyData();
        List<JourneyData> journeyDataList = new ArrayList<>();
        journeyDataList.add(journeyData);
        journeyData.setFlight_codes(Arrays.asList("AA9","AA10"));
        Mockito.when(journeyRepository.findListOfJourneys("New York", "Istanbul"))
                .thenReturn(journeyDataList);

        Flight flight1 = Flight.builder().code("AA9").carrier("American Airlines").cityFrom(cityRepository.getByName("New York")).cityTo(cityRepository.getByName("Los Angeles")).start(dateTime).end(dateTime2).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build();
        Flight flight2 = Flight.builder().code("AA10").carrier("American Airlines").cityFrom(cityRepository.getByName("Los Angeles")).cityTo(cityRepository.getByName("Istanbul")).start(dateTime2).end(dateTime3).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build();
        Mockito.when(flightRepository.getByCode("AA9"))
                .thenReturn(flight1);
        Mockito.when(flightRepository.getByCode("AA10"))
                .thenReturn(flight2);


    }


    @Test
    public void testGetJourney_shouldReturnJourney() {
        SearchFlightDto searchFlightDto = new SearchFlightDto();
        searchFlightDto.setCityFrom("New York");
        searchFlightDto.setCityTo("Istanbul");
        searchFlightDto.setCountAdult(1);
        searchFlightDto.setCountChildren(0);
        searchFlightDto.setTicketType("economy");
        List<Journey> journeys= journeyService.getJourney(searchFlightDto);
        assertThat(journeys.size()).isEqualTo(1);
        assertThat(journeys.get(0).getPriceJourney()).isEqualTo(600);
        assertThat(journeys.get(0).getFlights().size()).isEqualTo(2);

    }
    @Test
    public void testGetJourney_shouldReturnNullJourney() {
        SearchFlightDto searchFlightDto = new SearchFlightDto();
        searchFlightDto.setCityFrom("New York");
        searchFlightDto.setCityTo("Istanbul");
        searchFlightDto.setCountAdult(40);
        searchFlightDto.setCountChildren(0);
        searchFlightDto.setTicketType("economy");
        List<Journey> journeys= journeyService.getJourney(searchFlightDto);
        assertThat(journeys.size()).isEqualTo(0);

    }


}