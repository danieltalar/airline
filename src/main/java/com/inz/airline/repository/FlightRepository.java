package com.inz.airline.repository;

import com.inz.airline.domain.Flight;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends Neo4jRepository<Flight, String> {

    Flight getByCode(String code);

    @Query("MATCH (source:City {name:{0}}) -[:FLYING_FROM]-(f:Flight)-[:FLYING_TO] - (dest:City {name:{1}}) RETURN f")
    List<Flight> findListOfFlights(String cityFrom, String cityTo);

}
