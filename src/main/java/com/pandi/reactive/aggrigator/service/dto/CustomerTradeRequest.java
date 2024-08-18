package com.pandi.reactive.aggrigator.service.dto;

import com.pandi.reactive.aggrigator.service.domain.Ticker;
import com.pandi.reactive.aggrigator.service.domain.TradeType;
import jakarta.validation.constraints.NotNull;

public record CustomerTradeRequest(@NotNull(message = "quantity cannot be blank") Integer quantity, Ticker ticker, TradeType tradeType) {
}
