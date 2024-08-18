package com.pandi.reactive.aggrigator.service.controller;


import com.pandi.reactive.aggrigator.service.dto.CustomerInfo;
import com.pandi.reactive.aggrigator.service.dto.CustomerTradeRequest;
import com.pandi.reactive.aggrigator.service.dto.TradeResponse;
import com.pandi.reactive.aggrigator.service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    @RequestMapping("/customer/{id}")
    public Mono<CustomerInfo> getCustomerInfo(@PathVariable Integer id) {

        return customerService.getCustomerInfo(id);
    }

    @PostMapping("/trade/{customerId}")
    public Mono<TradeResponse> processTrade(@PathVariable Integer customerId, @RequestBody @Valid CustomerTradeRequest customerTradeRequest) {

        return customerService.processTrade(customerId, customerTradeRequest);
    }


}
