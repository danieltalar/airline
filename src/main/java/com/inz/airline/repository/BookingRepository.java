package com.inz.airline.repository;

import com.inz.airline.domain.Booking;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends Neo4jRepository<Booking, Long> {

    List<Booking> getByOwner(String owner);

}
