package com.inz.airline.repository;

import com.inz.airline.domain.Ticket;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends Neo4jRepository<Ticket, Long> {

    List<Ticket> getByCode(String code);

}
