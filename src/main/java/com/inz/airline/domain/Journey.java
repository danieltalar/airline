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
import java.util.concurrent.atomic.AtomicReference;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journey {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate journey_date;
    //aby bylo od razu wiadomo po pobraniu Journey od jakiego miasta do jakiego ktos wybral podroz
    private String cityFrom;
    private String cityTo;
    private Double price;

    @Relationship(type = "BY_FLIGHT", direction = Relationship.OUTGOING)
    private List<Flight> flights;

    void getPrice(String flightClass, Integer numberOfAdults, Integer numberOfChildren){
        AtomicReference<Double> price = new AtomicReference<Double>(new Double(0));
        flights.forEach(flight -> price.updateAndGet(v -> v + flight.getPrice(flightClass, numberOfAdults, numberOfChildren)));
        this.price = price.get();
    }

    public Journey(LocalDate journey_date, List<Flight> flights) {
        this.journey_date = journey_date;
        this.flights = flights;
    }
}
