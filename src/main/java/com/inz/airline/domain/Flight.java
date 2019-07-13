package com.inz.airline.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {



    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String carrier;
    private Integer duration;
    private Integer arrival;
    private String destination_airport_code;
    private String source_airport_code;

    public Flight(String code, String carrier, Integer duration, Integer arrival, String destination_airport_code, String source_airport_code) {
        this.code = code;
        this.carrier = carrier;
        this.duration = duration;
        this.arrival = arrival;
        this.destination_airport_code = destination_airport_code;
        this.source_airport_code = source_airport_code;
    }

//    "AA9", "American Airlines", 314,114 ,"JFK", :"LAX"
//            source_airport_code:"JFK", departure:1300,
//            destination_airport_code:"LAX", arrival:114})
    //    source_airport_code:"JFK", departure:1300,
//    destination_airport_code:"LAX", arrival:114

//    @Relationship(type = "FLYING_FROM")
//    private  City source;
//    @Relationship(type = "FLYING_TO")
//    private  City destination;


}
