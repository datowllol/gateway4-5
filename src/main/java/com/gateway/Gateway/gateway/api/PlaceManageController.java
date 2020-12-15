package com.gateway.Gateway.gateway.api;


import com.gateway.Gateway.gateway.dto.Visitors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/visitors")
@NoArgsConstructor
public class PlaceManageController {




    private final String url = "http://pub-visitors:8083/visitors";

    @PostMapping()
    public Visitors addVisitors(@RequestBody Visitors visitors) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Visitors> result =
                restTemplate.postForEntity(url, visitors, Visitors.class);
        return result.getBody();
    }


    @GetMapping()
    public List<Visitors> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Visitors>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("/{visitorId}")
    public Visitors getById(@PathVariable(value = "visitorId") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Visitors> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }


    @DeleteMapping("/{visitorId}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "visitorId") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url + "/" + id.toString());
        return ResponseEntity.noContent().build();
    }
}
