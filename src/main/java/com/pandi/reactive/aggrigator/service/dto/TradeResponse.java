package com.pandi.reactive.aggrigator.service.dto;


import com.pandi.reactive.aggrigator.service.domain.Ticker;
import com.pandi.reactive.aggrigator.service.domain.TradeType;

public record TradeResponse(Integer customerId,
                            Ticker ticker,
                            Integer price,
                            Integer quantity,
                            TradeType action,
                            Integer totalPrice,
                            Integer balance) {
}
