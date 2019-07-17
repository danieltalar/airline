package com.inz.airline.repository;

import com.inz.airline.domain.Journey;
import com.inz.airline.domain.Passenger;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends Neo4jRepository<Passenger, Long> {

}
