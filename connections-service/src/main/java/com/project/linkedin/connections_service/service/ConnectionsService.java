package com.project.linkedin.connections_service.service;

import com.project.linkedin.connections_service.entity.PersonEntity;
import com.project.linkedin.connections_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<PersonEntity> getFirstDegreeConnections (Long userId) {
        log.info("Getting first degree connections for user with id : {}", userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

    public PersonEntity createPerson(PersonEntity personEntity) {
        log.info("Creating a person entity.");
        return personRepository.save(personEntity);
    }
}
