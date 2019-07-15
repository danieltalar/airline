package com.inz.airline.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    private String code;
    private String carrier;
    private Integer duration;
    private Integer arrival;
    private String destination_airport_code;
    private String source_airport_code;

    @Relationship(type = "FLYING_FROM", direction = Relationship.INCOMING)
    private City cityFrom;

    @Relationship(type = "FLYING_TO")
    private City cityTo;

    public Flight(String code, String carrier, Integer duration, Integer arrival, String destination_airport_code, String source_airport_code, City cityFrom, City cityTo) {
        this.code = code;
        this.carrier = carrier;
        this.duration = duration;
        this.arrival = arrival;
        this.destination_airport_code = destination_airport_code;
        this.source_airport_code = source_airport_code;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        System.out.println(cityFrom);
        System.out.println(cityTo);
        System.out.println(this.cityTo);

    }
}
