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

//    @Relationship(type = "FLYING_FROM")
//    private  City source;
//    @Relationship(type = "FLYING_TO")
//    private  City destination;


}
