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
public class Passenger {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer age;

    @Relationship(type = "IS_USER", direction = Relationship.OUTGOING)
    private User user;

    public Passenger(String email, String first_name, String last_name, String gender, Integer age) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
    }

    public Passenger(String email, String first_name, String last_name, String gender, Integer age, User user) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
        this.user = user;
    }
}
