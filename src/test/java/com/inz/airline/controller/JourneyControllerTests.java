package com.inz.airline.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inz.airline.domain.City;
import com.inz.airline.domain.Flight;
import com.inz.airline.dto.SearchFlightDto;
import com.inz.airline.repository.CityRepository;
import com.inz.airline.service.FlightService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc(secure = false, addFilters = false)
@SpringBootTest
public class JourneyControllerTests {


    @Autowired
    FlightService flightService;

    @Autowired
    CityRepository cityRepository;


    @Before
    public void setUp(){

        LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 1, 20, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2019, Month.MARCH, 2, 2, 1);
        LocalDateTime dateTime3 = LocalDateTime.of(2019, Month.MARCH, 2, 12, 1);
        LocalDateTime dateTime4 = LocalDateTime.of(2019, Month.MARCH, 3, 20, 1);

        City citytest1 = new City ("New York Test", "");
        City citytest2 = new City ("Los Angeles Test", "");
        City citytest3 = new City ("Istanbul Test", "");
        cityRepository.save(citytest1);
        cityRepository.save(citytest2);
        cityRepository.save(citytest3);

        Flight flighttest1 =  Flight.builder().code("AA29TEST").carrier("American Airlines").cityFrom(cityRepository.getByName("New York Test")).cityTo(cityRepository.getByName("Los Angeles Test")).start(dateTime).end(dateTime2).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build();
        Flight flighttest2 =   Flight.builder().code("AA310TEST").carrier("American Airlines").cityFrom(cityRepository.getByName("Los Angeles Test")).cityTo(cityRepository.getByName("Istanbul Test")).start(dateTime2).end(dateTime4).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build();
        flightService.addFlight(flighttest1 );
        flightService.addFlight(flighttest2 );


    }
    @After
    public void clean(){
        cityRepository.deleteById(cityRepository.getByName("New York Test").getId());
        cityRepository.deleteById(cityRepository.getByName("Los Angeles Test").getId());
        cityRepository.deleteById(cityRepository.getByName("Istanbul Test").getId());
        flightService.deleteFlight("AA29TEST");
        flightService.deleteFlight("AA310TEST");
    }


    @Autowired
    private MockMvc mvc;


    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
        SearchFlightDto searchFlightDto = new SearchFlightDto();
        searchFlightDto.setCityTo("Istanbul Test");
        searchFlightDto.setCityFrom("New York Test");
        searchFlightDto.setCountAdult(1);
        searchFlightDto.setCountChildren(3);
        searchFlightDto.setTicketType("economy");
       // searchFlightDto.setDataStartSearch(LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(searchFlightDto);
        MvcResult result = this.mvc.perform(post("/api/journey")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(jsonPath("$").hasJsonPath())
                .andDo(print()).andExpect(jsonPath(".priceJourney").value(Double.valueOf(1950)))
                .andDo(print()).andExpect(jsonPath(".cityFromJourney").value("New York Test"))
                .andExpect(status().is2xxSuccessful())

                .andReturn();

        System.out.println(result.getResponse().getContentAsString());



    }
}
