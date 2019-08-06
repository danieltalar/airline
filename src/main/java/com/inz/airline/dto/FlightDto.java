package com.inz.airline.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDto {

    private String code;
    private String carrier;
    private Integer duration;
    private Integer total_seat_number;
    private Integer avaiable_seats;
    private Integer distance;
    private LocalDateTime start;
    private LocalDateTime end;


}
