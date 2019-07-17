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
public class Journey {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate flight_date;

    @Relationship(type = "BY_FLIGHT", direction = Relationship.OUTGOING)
    private List<Flight> flights;

    public Journey(LocalDate flight_date, List<Flight> flights) {
        this.flight_date = flight_date;
        this.flights = flights;
    }
}
