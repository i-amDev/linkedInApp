package com.project.linkedin.connections_service.controller;

import com.project.linkedin.connections_service.entity.PersonEntity;
import com.project.linkedin.connections_service.service.ConnectionsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/first-degree")
    public ResponseEntity<List<PersonEntity>> getFirstConnections () {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections());
    }

    @PostMapping("/createPerson")
    public PersonEntity createPerson(@RequestBody PersonEntity personEntity) {
        return connectionsService.createPerson(personEntity);
    }
}
