package com.inz.airline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
@AllArgsConstructor
@Data
@NoArgsConstructor
public class JourneyData {
    List<Flight> flights;
}
