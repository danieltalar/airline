package com.inz.airline.service.impl;

import com.inz.airline.domain.*;
import com.inz.airline.dto.BookingDto;
import com.inz.airline.repository.*;
import com.inz.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    PassengerRepository passengerRepository;




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


        Journey journey = bookingDto.getJourney();
        journey.setFlights(flights);
        journeyRepository.save(journey);
        journey.setInterchangeAccount(flights.size() - 1);
        booking.setJourney(journey);
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
            booking.setBooking_date(LocalDate.now());
            booking.setOwner(bookingDto.getOwner());
            booking.setTickets(tickets);
            booking.setPassengers(bookingDto.getPassengerList());
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        Long journeyid = booking.getJourney().getId();
        Journey journey1 = journeyRepository.findById(journeyid).get();
        String flightClass = booking.getTickets().get(0).getFlight_class();
        for (int i = 0; i < journey1.getFlights().size() ; i++) {
            Flight byCode = flightRepository.getByCode(booking.getJourney().getFlights().get(i).getCode());
            byCode.deleteTickets(flightClass, booking.getPassengers().size());
            flightRepository.save(byCode);
        }
        journeyRepository.delete(journey1);
        for (Passenger passenger : booking.getPassengers()){
            passengerRepository.delete(passenger);
        }
        
        bookingRepository.delete(booking);

    }

    @Override
    public List<Booking> getMyReservations(String owner) {
        List<Booking> byOwner = bookingRepository.getByOwner(owner);
        for (Booking booking : byOwner) {

            Long id = booking.getJourney().getId();
            Optional<Journey> byId = journeyRepository.findById(id);
            List <Flight> flights = new ArrayList<>();
            for (Flight flight: byId.get().getFlights()) {
                Flight byCode = flightRepository.getByCode(flight.getCode());
                flights.add(byCode);
            }
            byId.get().setFlights(flights);
            booking.setJourney(byId.get());


        }

        return byOwner;
    }
}
