package com.inz.airline.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;


@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight implements Comparable<Flight> {

    @Id
    private String code;
    private String carrier;
    private Integer avaiableSeatsEconomy;
    private Integer avaiableSeatsPremiumEconomy;
    private Integer avaiableSeatsFirstClass;
    private Integer avaiableSeatsBussinesClass;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;
    private Double basePrice;


    @Relationship(type = "FLYING_FROM", direction = Relationship.INCOMING)
    private City cityFrom;

    @Relationship(type = "FLYING_TO")
    private City cityTo;

    public double takePrice(String flightClass, Integer accountChildren, Integer accountAdults){
        double mnoznik=1;

        switch (flightClass) {
            case "economy": mnoznik = 1;break;
            case "premium economy":mnoznik = 1.15;break;
            case "business class": mnoznik = 1.30;break;
            case "first class": mnoznik =  1.45;break;
            default: break;
        }
        double price =0;
        for (int i = 0; i <accountAdults ; i++) {
               price+= mnoznik * basePrice;
        }
        for (int i = 0; i <accountChildren ; i++) {
            price+= mnoznik * basePrice * 0.75;
        }

        return  price;
    }



    public Boolean checkHasPlace(String flightClass, Integer accountChildren, Integer accountAdults){
        int account = accountChildren + accountAdults;
        Boolean response = false;
        switch (flightClass){
            case "economy":
                if (avaiableSeatsEconomy>=account) {
                response = true; }
                break;

            case "premium economy":if (avaiableSeatsPremiumEconomy>=account){
                response = true;}
                break;

            case "business class": if (avaiableSeatsBussinesClass>=account){
                response = true;}
                break;

            case "first class":if (avaiableSeatsFirstClass>=account){
                response = true;}
                break;

            default:
            }
            return  response;
        }

        public void deleteTickets(String flightClass, Integer account){

            switch (flightClass) {
                case "economy": this.avaiableSeatsEconomy +=account;break;
                case "premium economy":this.avaiableSeatsPremiumEconomy +=account;break;
                case "business class": this.avaiableSeatsBussinesClass +=account;break;
                case "first class": this.avaiableSeatsFirstClass +=account;break;
                default: break;
            }

        }





    @Override
    public int compareTo(Flight o) {
        return this.getStart().compareTo(o.getStart());
    }
}
