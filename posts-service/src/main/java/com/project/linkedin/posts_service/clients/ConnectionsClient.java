package com.project.linkedin.posts_service.clients;

import com.project.linkedin.posts_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service", path = "/connections_service")
public interface ConnectionsClient {

    @GetMapping("/connections/first-degree")
    public List<PersonDto> getFirstConnections (@PathVariable Long userId);


}
