package com.pandi.reactive.aggrigator.service.service;

import com.pandi.reactive.aggrigator.service.dto.CustomerInfo;
import com.pandi.reactive.aggrigator.service.dto.CustomerTradeRequest;
import com.pandi.reactive.aggrigator.service.dto.TradeRequest;
import com.pandi.reactive.aggrigator.service.dto.TradeResponse;
import com.pandi.reactive.aggrigator.service.exception.CustomerNotFoundException;
import com.pandi.reactive.aggrigator.service.exception.CustomerResponseException;
import com.pandi.reactive.aggrigator.service.exception.StockResponseException;
import com.pandi.reactive.aggrigator.service.exception.TradeResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Objects;

@Service
@Slf4j
public class CustomerService {


   private final WebClient customerWebClient;
   private final WebClient stockWebClient;

    public CustomerService(@Qualifier(value = "customerWebClient") WebClient customerWebClient, @Qualifier(value = "stockWebClient") WebClient stockWebClient) {
        this.customerWebClient = customerWebClient;
        this.stockWebClient = stockWebClient;
    }


    public Mono<CustomerInfo> getCustomerInfo(Integer id) {

        return this.customerWebClient.get()
                .uri("/customer/{id}", id)
                .retrieve()
                .bodyToMono(CustomerInfo.class)
                .onErrorResume(WebClientResponseException.BadRequest.class,e -> Mono.error(new CustomerNotFoundException("customer not found for"+id)))
                .onErrorResume(WebClientResponseException.class, this::findProblemDetail);
    }

    public <T> Mono<T> findProblemDetail(WebClientResponseException e){
        var problemDetail = e.getResponseBodyAs(ProblemDetail.class);
        var message = Objects.isNull(problemDetail)?e.getMessage():problemDetail.getDetail();
        return Mono.error(new CustomerResponseException(message));
    }


    public Mono<TradeResponse> alternativeApproach(Integer customerId,CustomerTradeRequest customerTradeRequest){

       Mono<HashMap> mapMono = this.stockWebClient.get()
                .uri("/stock/{ticker}",customerTradeRequest.ticker())
                .retrieve()
                .bodyToMono(HashMap.class);

        return mapMono.zipWhen(d ->
                tradeRequest(
                        new TradeRequest
                                (customerId,Integer.valueOf(d.get("price").toString()),customerTradeRequest.quantity(),customerTradeRequest.ticker(),customerTradeRequest.tradeType())))
                .map(tuple -> tuple.getT2());


    }

    public Mono<TradeResponse> processTrade(Integer customerId, CustomerTradeRequest customerTradeRequest) {

         return this.stockWebClient.get()
                  .uri("/stock/{ticker}",customerTradeRequest.ticker())
                  .retrieve()
                  .bodyToMono(HashMap.class)
                  .onErrorResume(WebClientResponseException.class,e-> Mono.error(new StockResponseException(e.getMessage())))
                  .map(map -> map.get("price"))
                  .map(price -> new TradeRequest(customerId,Integer.valueOf(price.toString()),customerTradeRequest.quantity(),customerTradeRequest.ticker(),customerTradeRequest.tradeType()))
                  .flatMap(d -> tradeRequest(d));

    }

    public Mono<TradeResponse> tradeRequest(TradeRequest tradeRequest){

       return this.customerWebClient.post()
                .uri("/customer/trade")
                .bodyValue(tradeRequest)
                .retrieve()
                .bodyToMono(TradeResponse.class)
                .onErrorResume(WebClientResponseException.class,e -> Mono.error(new TradeResponseException(e.getMessage())));
    }
}
