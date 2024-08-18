package com.pandi.reactive.aggrigator.service.dto;


import com.pandi.reactive.aggrigator.service.domain.Ticker;
import com.pandi.reactive.aggrigator.service.domain.TradeType;

public record TradeRequest(Integer customerId,
                           Integer price,
                           Integer quantity,
                           Ticker ticker, TradeType type
) {
}
