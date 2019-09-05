package com.inz.airline.controller;

import com.inz.airline.service.JourneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class JourneyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JourneyService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
//        SearchFlightDto searchFlightDto = new SearchFlightDto();
//        searchFlightDto.setCityTo("London");
//        searchFlightDto.setCityTo("Barcelona");
//
//        //Employee alex = new Employee("alex");
//
//        List<Journey> allEmployees = new ArrayList<>();
//
//
//        given(service.getJourney(searchFlightDto)).willReturn(allEmployees);
//
//        mvc.perform(get("/api/journey")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
    }

    // write test cases here
}
