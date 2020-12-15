package com.gateway.Gateway.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor

public final class Saloon {
    private UUID tableId;

    private int placeNum;

    private String uniqueName;

    private UUID occupiedTableId;

    private UUID freeTableId;

    public Saloon() {
    }

    public Saloon(String uniqueName, int placeNum) {
        this.placeNum=placeNum;
        this.uniqueName=uniqueName;
    }

}
