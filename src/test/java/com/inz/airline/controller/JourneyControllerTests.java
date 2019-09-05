package com.inz.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.inz.airline.dto.SearchFlightDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc(secure = false, addFilters = false)
@SpringBootTest
public class JourneyControllerTests {


    @Autowired
    private MockMvc mvc;


    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
        SearchFlightDto searchFlightDto = new SearchFlightDto();
        searchFlightDto.setCityTo("Istanbul");
        searchFlightDto.setCityFrom("New York");
        searchFlightDto.setCountAdult(1);
        searchFlightDto.setCountChildren(3);
        searchFlightDto.setTicketType("economy");
        searchFlightDto.setDataStartSearch(LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(searchFlightDto);

        mvc.perform(post("/api/journey").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());

    }
}
