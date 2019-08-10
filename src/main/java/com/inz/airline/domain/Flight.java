package com.inz.airline.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NodeEntity
@Data
@NoArgsConstructor
public class Flight implements Comparable<Flight> {

    @Id
    private String code;
    private String carrier;
    private Integer duration;
    private Integer total_seat_number;
    private Integer avaiable_seats;
    private Integer distance;
    private LocalDateTime start;
    private LocalDateTime end;


    @Relationship(type = "FLYING_FROM", direction = Relationship.INCOMING)
    private City cityFrom;

    @Relationship(type = "FLYING_TO")
    private City cityTo;

    @Relationship(type="CONTAINS")
    private List<Ticket> tickets;

//    public Double getPriceJourney(String flightClass, Integer numberOfAdults, Integer numberOfChildren){
//        List<Ticket> ticketsByFlightClassAdults = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& ticket.getIsAdult()).collect(Collectors.toList());
//        List<Ticket> ticketsByFlightClassChildren = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& !ticket.getIsAdult()).collect(Collectors.toList());
//        double sumAdults = 0;
//        double sumChildren = 0;
//
//        if (!ticketsByFlightClassAdults.isEmpty()){
//            sumAdults = ticketsByFlightClassAdults.get(0).getPriceJourney() * numberOfAdults;
//        }
//        if (!ticketsByFlightClassChildren.isEmpty()){
//            sumChildren = ticketsByFlightClassChildren.get(0).getPriceJourney() * numberOfChildren;
//        }
//        return sumAdults + sumChildren;
//    }

    public Boolean checkHasPlace(String flightClass, Integer numberOfAdults, Integer numberOfChildren){

        if (tickets.isEmpty()) return false;
        List<Ticket> ticketsByFlightClassAdults = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& ticket.getIsAdult()).collect(Collectors.toList());
        List<Ticket> ticketsByFlightClassChildren = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& ticket.getIsAdult()).collect(Collectors.toList());
        return ticketsByFlightClassAdults.size() >= numberOfAdults && ticketsByFlightClassChildren.size() >= numberOfChildren;
    }

    public Flight(String code, String carrier, City cityFrom, City cityTo, LocalDateTime start, LocalDateTime end, Integer total_seat_number) {
        this.code = code;
        this.carrier = carrier;
        this.duration = Math.toIntExact(ChronoUnit.MINUTES.between(start, end));
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.start = start;
        this.end = end;
        this.total_seat_number = total_seat_number;
        this.avaiable_seats = total_seat_number;
    }

    public Flight(String code, String carrier, City cityFrom, City cityTo, LocalDateTime start, LocalDateTime end, Integer total_seat_number,Integer distance,List<Ticket> tickets) {
        this.code = code;
        this.carrier = carrier;
        this.duration = Math.toIntExact(ChronoUnit.MINUTES.between(start, end));
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.start = start;
        this.end = end;
        this.total_seat_number = total_seat_number;
        this.avaiable_seats = total_seat_number;
        this.distance=distance;
        this.tickets=tickets;
    }


    public void deleteTickets(String ticketType, Integer countAdults, Integer countChildren){
        List<Ticket> collect = tickets.stream().filter(ticket -> ticket.getFlight_class().equals(ticketType)).collect(Collectors.toList());
        List<Ticket> ticketAdults = collect.stream().filter(ticket -> ticket.getIsAdult().equals(true)).collect(Collectors.toList());
        List<Ticket> ticketChildren= collect.stream().filter(ticket -> ticket.getIsAdult().equals(false)).collect(Collectors.toList());
        for (int i=0;i<countAdults;i++){
            ticketAdults.remove(0);
        }
        for (int i=0;i<countChildren;i++){
            ticketChildren.remove(0);
        }
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(ticketAdults);
        tickets.addAll(ticketChildren);
        setTickets(tickets);
    }


    @Override
    public int compareTo(Flight o) {
        return this.getStart().compareTo(o.getStart());
    }
}
