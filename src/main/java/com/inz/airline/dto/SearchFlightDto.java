package com.inz.airline.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.Month;

@Data
public class SearchFlightDto {

    private Integer priceMax = 99999999;
    private Integer priceMin = 0;
    private LocalDateTime dataStartSearch = LocalDateTime.of(2019, Month.FEBRUARY, 1, 20, 1, 40);
    private LocalDateTime dataEndSearch = LocalDateTime.MAX;
    private String cityTo;
    private String cityFrom;
    private Integer countAdult;
    private Integer countChildren;
    private String ticketType;
}
