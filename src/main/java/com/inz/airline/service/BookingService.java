package com.inz.airline.service;

import com.inz.airline.domain.Booking;
import com.inz.airline.dto.BookingDto;

import java.util.List;

public interface BookingService {

    Booking addBooking(BookingDto bookingDto);
    void cancelBooking(Long id);
    List<Booking> getMyReservations(String owner);
}
