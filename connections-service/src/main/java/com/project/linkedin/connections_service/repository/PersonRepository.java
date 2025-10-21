package com.project.linkedin.connections_service.repository;

import com.project.linkedin.connections_service.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {

    Optional<PersonEntity> getByName(String name);

    @Query("MATCH (personA:person) - [:CONNECTED-TO]- (personB:person) WHERE personA.userId = $userId RETURN personB")
    List<PersonEntity> getFirstDegreeConnections(Long userId);
}
