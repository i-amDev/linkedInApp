package com.project.linkedin.connections_service.controller;

import com.project.linkedin.connections_service.entity.PersonEntity;
import com.project.linkedin.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/first-degree/{userId}")
    public ResponseEntity<List<PersonEntity>> getFirstConnections (@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }

    @PostMapping("/createPerson")
    public PersonEntity createPerson(@RequestBody PersonEntity personEntity) {
        return connectionsService.createPerson(personEntity);
    }
}
