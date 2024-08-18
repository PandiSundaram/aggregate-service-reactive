package com.pandi.reactive.aggrigator.service.dto;

import com.pandi.reactive.aggrigator.service.domain.Ticker;

import java.time.LocalDateTime;

public record PriceUpdate(Ticker ticker,
                          Integer price,
                          LocalDateTime time) {
}
