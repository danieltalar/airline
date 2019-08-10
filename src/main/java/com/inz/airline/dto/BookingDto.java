package com.inz.airline.dto;

import com.inz.airline.domain.Journey;
import com.inz.airline.domain.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class BookingDto {

    List<Passenger> passengerList;
    Integer accountAdults;
    Integer accountChildren;
    String ticketType;
    Journey journey;
}
