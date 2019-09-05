package com.inz.airline.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;

@NodeEntity
@Data
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate dateOfBirth;

    @Relationship(type = "IS_USER", direction = Relationship.OUTGOING)
    private User user;

    public Passenger(String first_name, String last_name, String gender, LocalDate dateOfBirth, User user) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
    }
}
