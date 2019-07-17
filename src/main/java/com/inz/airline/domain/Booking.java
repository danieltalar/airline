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
    private LocalDate reservation_date;

    @Relationship(type = "HAS_JOURNEY", direction = Relationship.OUTGOING)
    private Journey journey;

    @Relationship(type = "HAS_PASSENGER", direction = Relationship.OUTGOING)
    private List<Passenger> passengers;

    public Booking(LocalDate reservation_date, Journey journey, List<Passenger> passengers) {
        this.reservation_date = reservation_date;
        this.journey = journey;
        this.passengers = passengers;
    }
}
