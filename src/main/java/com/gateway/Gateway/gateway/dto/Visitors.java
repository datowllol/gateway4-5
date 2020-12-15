package com.gateway.Gateway.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public final class Visitors {


    private UUID visitorId;
    private int visitorsNum;
    private UUID occupiedTableID;


    public Visitors() {

    }


}