package com.inz.airline.repository;

import com.inz.airline.domain.City;
import com.inz.airline.domain.Journey;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends Neo4jRepository<Journey, Long> {

}
