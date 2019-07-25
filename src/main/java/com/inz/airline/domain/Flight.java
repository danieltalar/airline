package com.inz.airline.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@NodeEntity
@Data
@NoArgsConstructor
public class Flight {

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

    public Double getPriceTicketByType(String flightClass){
        List<Ticket> collect = tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass)).collect(Collectors.toList());
        return collect.get(0).getPrice();
    }

    public Boolean checkHasPlace(String flightClass, Integer numberOfAdults, Integer numberOfChildren){

        List<Ticket> ticketsByFlightClassAdults = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& ticket.getIsAdult()).collect(Collectors.toList());
        List<Ticket> ticketsByFlightClassChildren = this.tickets.stream().filter(ticket -> ticket.getFlight_class().equals(flightClass )&& ticket.getIsAdult()).collect(Collectors.toList());
        if (ticketsByFlightClassAdults.size()>=numberOfAdults && ticketsByFlightClassAdults.size()>=numberOfChildren) return true;
        else return false;
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
}
