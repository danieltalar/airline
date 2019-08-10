package com.inz.airline.service.impl;

import com.inz.airline.domain.Booking;
import com.inz.airline.dto.BookingDto;
import com.inz.airline.repository.BookingRepository;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    FlightRepository flightRepository;




    @Override
    public Booking addBooking(BookingDto bookingDto) {
//            Booking booking = new Booking();
//         //   booking.setPassengers(booking.getPassengers());
//        //    booking.setJourney(bookingDto.getJourney());
//        bookingDto.getJourney().getFlights().forEach(flight -> flight.deleteTickets(bookingDto.getTicketType(), bookingDto.getAccountAdults(), bookingDto.getAccountChildren()));
//        bookingDto.getJourney().getFlights().forEach(flight -> flightRepository.save(flight));
//            bookingRepository.save(booking);

        return null;
    }
}
