package com.pandi.reactive.aggrigator.service.controller;


import com.pandi.reactive.aggrigator.service.dto.PriceUpdate;
import com.pandi.reactive.aggrigator.service.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class StockStreamController {


    private final StockService stockService;

    @GetMapping(value = "/price/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PriceUpdate> streamPriceUpdate(){

        return stockService.getStockPriceStream();

    }

}
