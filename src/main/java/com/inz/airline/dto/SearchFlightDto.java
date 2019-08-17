package com.inz.airline.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchFlightDto {

    private Integer priceMax = 99999999;
    private Integer priceMin = 0;
    private LocalDateTime dataStartSearch = LocalDateTime.MIN;
    private String cityTo;
    private String cityFrom;
    private Integer countAdult;
    private Integer countChildren;
    private String ticketType;
}
