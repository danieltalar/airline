package com.inz.airline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NodeEntity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private String flight_class;
    private String code;
    private Double price;
    private Boolean isAdult;

    public Ticket(String flight_class, String code, Boolean isAdult, Double price) {
        this.flight_class = flight_class;
        this.code = code;
        this.isAdult = isAdult;
        this.price = price;
    }

//    private double setPrice(Double price) {
//        switch (flight_class) {
//            case "economy": this.price = price;break;
//            case "premium economy": this.price = price * 1.15;break;
//            case "business class": this.price = price * 1.30;break;
//            case "first class": this.price = price * 1.45;break;
//            default: this.price = price;break;
//        }
//        if (!isAdult) this.price *= 0.75;
//        return this.price;
//    }
}
