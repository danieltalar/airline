package com.inz.airline.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String country;

    @Relationship(type = "FLYING_FROM", direction = Relationship.INCOMING)
    private List<FlyingFrom> flyingFroms;

    @Relationship(type = "FLYING_TO", direction = Relationship.INCOMING)
    private List<FlyingTo> flyingTos ;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }
}