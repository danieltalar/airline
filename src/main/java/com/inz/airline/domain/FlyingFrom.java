package com.inz.airline.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "FLYING_FROM")
@Data
@NoArgsConstructor
public class FlyingFrom {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private City city;

    @EndNode
    private Flight flight;

    public FlyingFrom(City city, Flight flight) {
        this.city = city;
        this.flight = flight;
    }
}
