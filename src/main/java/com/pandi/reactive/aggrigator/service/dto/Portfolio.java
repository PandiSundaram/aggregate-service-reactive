package com.pandi.reactive.aggrigator.service.dto;


import com.pandi.reactive.aggrigator.service.domain.Ticker;

public record Portfolio(Ticker ticker, Integer quantity) {
}
