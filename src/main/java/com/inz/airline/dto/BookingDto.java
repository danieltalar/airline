package com.inz.airline.dto;

import com.inz.airline.domain.Journey;
import lombok.Data;

@Data
public class BookingDto {

//    List<Passenger> passengerList = new ArrayList<>();
    Integer accountAdults;
    Integer accountChildren;
    String ticketType;
    Journey journey;
}
