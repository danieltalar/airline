package com.inz.airline.service;

import com.inz.airline.domain.Booking;
import com.inz.airline.dto.BookingDto;

public interface BookingService {

    Booking addBooking(BookingDto bookingDto);
}
