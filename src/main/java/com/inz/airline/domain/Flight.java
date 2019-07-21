package com.inz.airline.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NodeEntity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    private String code;
    private String carrier;
    private Integer duration;
    private Integer max_seats;
    private Integer avaiable_seats;
    private LocalDateTime start;
    private LocalDateTime end;

    @Relationship(type = "FLYING_FROM", direction = Relationship.INCOMING)
    private City cityFrom;

    @Relationship(type = "FLYING_TO")
    private City cityTo;

    public Flight(String code, String carrier, City cityFrom, City cityTo, LocalDateTime start, LocalDateTime end, Integer max_seats ) {
        this.code = code;
        this.carrier = carrier;
        this.duration = Math.toIntExact(ChronoUnit.MINUTES.between(start, end));
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.start = start;
        this.end = end;
        this.max_seats = max_seats;
        this.avaiable_seats = max_seats;




    }
}
