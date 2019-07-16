package com.inz.airline.repository;

import com.inz.airline.domain.Flight;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends Neo4jRepository<Flight, String> {

    Flight getByCode(String code);

}
