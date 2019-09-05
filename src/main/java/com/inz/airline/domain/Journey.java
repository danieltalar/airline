package com.inz.airline.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.List;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journey {

    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime journey_start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime journey_finish;
    private String cityFrom;
    private String cityTo;
    private Double priceJourney;
    private Double priceForChildren;
    private Double priceForAdults;
    private Integer interchangeAccount;
    private Long duration;
    @Relationship(type = "BY_FLIGHT", direction = Relationship.OUTGOING)
    private List<Flight> flights;


}
