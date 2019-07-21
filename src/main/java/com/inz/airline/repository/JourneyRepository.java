package com.inz.airline.repository;

import com.inz.airline.domain.City;
import com.inz.airline.domain.Flight;
import com.inz.airline.domain.Journey;
import com.inz.airline.domain.JourneyData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends Neo4jRepository<Journey, Long> {

    @Query("MATCH path = (cityFrom:City{name:{0}})-[:FLYING_FROM|FLYING_TO*0..6]->" +
            "(cityTo:City{name: {1} }) WITH FILTER(f in nodes(path) WHERE 'Flight' IN labels(f)) " +
            "as flights,FILTER(city in nodes(path) " +
            "WHERE 'City' IN labels(city)) as " +
            " cities RETURN flights")
    List<JourneyData> findListOfJourneys(String cityFrom, String cityTo);

}
