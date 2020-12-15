package com.gateway.Gateway.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.UUID;


@Data
@AllArgsConstructor
public final class SoldBeer {

    private UUID soldBeerId;
    private int moneyGain;
    private String beerType;
    private UUID visitorsId;

    public SoldBeer() {

    }

    public SoldBeer(int moneyGain, String beerType) {
        this.moneyGain = moneyGain;
        this.beerType = beerType;
    }


}
