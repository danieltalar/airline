package com.inz.airline.service.impl;

import com.inz.airline.domain.Booking;
import com.inz.airline.domain.Flight;
import com.inz.airline.domain.Ticket;
import com.inz.airline.dto.BookingDto;
import com.inz.airline.repository.BookingRepository;
import com.inz.airline.repository.FlightRepository;
import com.inz.airline.repository.JourneyRepository;
import com.inz.airline.repository.TicketRepository;
import com.inz.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketRepository ticketRepository;




    @Override
    public Booking addBooking(BookingDto bookingDto) {
            Booking booking = new Booking();
//            booking.setPassengers(booking.getPassengers());

            booking.setJourney(bookingDto.getJourney());


//            bookingDto.getJourney().getFlights().forEach(flight -> flight.deleteTickets(bookingDto.getTicketType(), bookingDto.getAccountAdults(), bookingDto.getAccountChildren()));
//            bookingDto.getJourney().getFlights().forEach(flight -> flightRepository.save(flight));
        List<Flight> flights = booking.getJourney().getFlights();
        Long id = ticketRepository.getByCode(flights.get(0).getCode()).get(0).getId();
        ticketRepository.deleteById(id);
        List<List<Ticket>> list = new ArrayList<>();
        bookingDto.getJourney().getFlights().forEach(flight -> System.out.println(ticketRepository.getByCode(flight.getCode()).size()));
            bookingDto.getJourney().getFlights().forEach(flight -> ticketRepository.getByCode(flight.getCode()));
        System.out.println("sIZY po : ");
        bookingDto.getJourney().getFlights().forEach(flight -> System.out.println(ticketRepository.getByCode(flight.getCode()).size()));
        return bookingRepository.save(booking);

    }
}
