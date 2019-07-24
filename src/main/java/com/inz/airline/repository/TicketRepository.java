package com.inz.airline.repository;

import com.inz.airline.domain.Booking;
import com.inz.airline.domain.Ticket;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends Neo4jRepository<Ticket, Long> {

}
