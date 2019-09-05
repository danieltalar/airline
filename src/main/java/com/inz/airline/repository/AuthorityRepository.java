package com.inz.airline.repository;

import com.inz.airline.model.Authority;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AuthorityRepository extends Neo4jRepository<Authority, Long> {
  Authority findByName(String name);
}
