package com.pandi.reactive.aggrigator.service.service;


import com.pandi.reactive.aggrigator.service.dto.PriceUpdate;
import com.pandi.reactive.aggrigator.service.exception.StockResponseException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class StockService {

    private final WebClient stockWebClient;

    public StockService(@Qualifier(value="stockWebClient") WebClient webClient) {
        this.stockWebClient = webClient;
    }

    public Flux<PriceUpdate> getStockPriceStream() {

        return this.stockWebClient.get()
                .uri("/stock/price-stream")
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(PriceUpdate.class)
                .onErrorResume(WebClientResponseException.class,e -> Mono.error(new StockResponseException(e.getMessage())));

    }
}
