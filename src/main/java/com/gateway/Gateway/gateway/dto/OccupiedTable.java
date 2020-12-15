package com.gateway.Gateway.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;



import java.util.UUID;


@Data
@AllArgsConstructor
public final class OccupiedTable {


    private UUID occupiedTableId;

    private UUID visitorsId;

    private UUID saloonId;

    public OccupiedTable() {

    }
}