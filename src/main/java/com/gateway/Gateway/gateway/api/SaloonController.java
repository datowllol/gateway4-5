package com.gateway.Gateway.gateway.api;


import com.gateway.Gateway.gateway.dto.Saloon;
import com.gateway.Gateway.gateway.dto.FreeTable;
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
@RequestMapping("/saloon")
@NoArgsConstructor
public class SaloonController {
    private final String url = "http://pub-saloon:8084/saloon";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${saloon.routing-key}")
    private String routingKey;

    @PostMapping()
    public Saloon addSaloon(@RequestBody Saloon saloon) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.postForEntity(url, saloon, Saloon.class);
        return result.getBody();
    }

    @PostMapping("mq")
    public String addSaloonMq(@RequestBody Saloon saloon) {
        rabbitTemplate.convertAndSend(exchange, routingKey, saloon);
        return "Sent hello world";
    }

    @PostMapping("getbynum")
    public Saloon getByNumber(@RequestBody Visitors visitorsDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.postForEntity(url+ "/getbynum", visitorsDTO, Saloon.class);
        return result.getBody();
    }

    @PostMapping("setfree")
    public FreeTable setFreeTable(@RequestBody FreeTable freeTable) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FreeTable> result =
                restTemplate.postForEntity(url + "/setfree", freeTable, FreeTable.class);
        return result.getBody();
    }

    @GetMapping()
    public List<Saloon> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Saloon>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }


    @GetMapping("/{tableID}")
    public Saloon getById(@PathVariable(value = "tableID") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @DeleteMapping("/{tableID}")
    public ResponseEntity<Void> deleteSaloonById(@PathVariable(value = "tableID") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url + "/" + id.toString());
        return ResponseEntity.noContent().build();
    }
}