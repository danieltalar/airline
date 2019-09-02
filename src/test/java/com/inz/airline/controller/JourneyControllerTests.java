package com.inz.airline.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(JourneyController.class)
public class JourneyControllerTests {

//    @Autowired
//    private MockMvc mvc;

//    @MockBean
//    private JourneyService service;

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