package com.inz.airline.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    private String code;
    private String carrier;
    private Integer duration;
    private Integer arrival;
    private String destination_airport_code;
    private String source_airport_code;

}
