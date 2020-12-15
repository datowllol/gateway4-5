package com.gateway.Gateway.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public final class FreeTable {

    private UUID freeTableId;
    private UUID saloonId;

    public FreeTable(){

    }

}