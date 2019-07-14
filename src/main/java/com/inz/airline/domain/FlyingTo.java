package com.inz.airline.domain;



import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "FLYING_TO")
@Data
@NoArgsConstructor
public class FlyingTo {

    @Id
    @GeneratedValue
    private Long id;

    @EndNode
    private City city;

    @StartNode
    private Flight flight;

    public FlyingTo(City city, Flight flight) {
        this.city = city;
        this.flight = flight;
    }
}
