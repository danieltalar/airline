package com.inz.airline.repository;

import com.inz.airline.domain.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends Neo4jRepository<City, Long> {

    City getByName(String name);
}
