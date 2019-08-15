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
            int account = bookingDto.getAccountAdults() + bookingDto.getAccountChildren();
            String ticketType =  bookingDto.getTicketType();
            List<Flight> flights = bookingDto.getJourney().getFlights();
            switch (ticketType)
            {
                case "economy":
                    flights.forEach(flight -> flight.setAvaiableSeatsEconomy(flight.getAvaiableSeatsEconomy() - account));
                    break;

                case "premium economy":
                    flights.forEach(flight -> flight.setAvaiableSeatsPremiumEconomy(flight.getAvaiableSeatsPremiumEconomy() - account));
                    break;

                case "business class":
                    flights.forEach(flight -> flight.setAvaiableSeatsBussinesClass(flight.getAvaiableSeatsBussinesClass() - account));
                    break;

                case "first class":
                    flights.forEach(flight -> flight.setAvaiableSeatsFirstClass(flight.getAvaiableSeatsFirstClass() - account));
                    break;

                default:
            }
            flightRepository.saveAll(flights);
            booking.setJourney(bookingDto.getJourney());
            List<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < bookingDto.getAccountAdults(); i++) {
                for (Flight flight: bookingDto.getJourney().getFlights()) {

                    Ticket ticket = new Ticket(bookingDto.getTicketType(), flight.getCode(), true, flight.getBasePrice());
                    tickets.add(ticket);
                }
            }
            for (int i = 0; i < bookingDto.getAccountChildren(); i++) {
                for (Flight flight: bookingDto.getJourney().getFlights()) {
                    Ticket ticket = new Ticket(bookingDto.getTicketType(), flight.getCode(), false, flight.getBasePrice());
                    tickets.add(ticket);
                }
            }





            booking.setTickets(tickets);
            booking.setPassengers(bookingDto.getPassengerList());
        return bookingRepository.save(booking);
    }
}
