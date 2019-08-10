package com.inz.airline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journey {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime journey_start;
    private LocalDateTime journey_finish;
    //aby bylo od razu wiadomo po pobraniu Journey od jakiego miasta do jakiego ktos wybral podroz
    private String cityFrom;
    private String cityTo;
    private Double priceJourney;

    @Relationship(type = "BY_FLIGHT", direction = Relationship.OUTGOING)
    private List<Flight> flights;

//    public Double Price(String flightClass, Integer numberOfAdults, Integer numberOfChildren){
//        AtomicReference<Double> priceJourney = new AtomicReference<Double>(new Double(0));
//        flights.forEach(flight -> priceJourney.updateAndGet(v -> v + flight.getPriceJourney(flightClass, numberOfAdults, numberOfChildren)));
//        System.out.println("obecna cena + " + priceJourney.get());
//        return priceJourney.get();
//    }

    public Journey(LocalDate journey_date, List<Flight> flights) {
        this.flights = flights;
    }
}
