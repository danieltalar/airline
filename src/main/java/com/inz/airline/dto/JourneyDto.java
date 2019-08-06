package com.inz.airline.dto;

import com.inz.airline.domain.Flight;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JourneyDto {

    private Long id;
    private LocalDateTime journey_start;
    private LocalDateTime journey_finish;
    private String cityFrom;
    private String cityTo;
    private Double price;
    private List<Flight> flights;
}
