package com.inz.airline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.List;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate booking_date;
    private String owner;

    @Relationship(type = "HAS_JOURNEY", direction = Relationship.OUTGOING)
    private Journey journey;

    @Relationship(type = "HAS_PASSENGER", direction = Relationship.OUTGOING)
    private List<Passenger> passengers;

    @Relationship(type = "HAS_TICKETS", direction = Relationship.OUTGOING)
    private List<Ticket> tickets;

    public Booking(LocalDate booking_date, Journey journey, List<Passenger> passengers) {
        this.booking_date = booking_date;
        this.journey = journey;
        this.passengers = passengers;
    }
}
