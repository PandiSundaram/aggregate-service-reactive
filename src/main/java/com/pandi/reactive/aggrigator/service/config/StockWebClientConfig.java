package com.pandi.reactive.aggrigator.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class StockWebClientConfig {

    @Value("${stock.service.url}")
    private String stockUrl;

    @Qualifier("stockWebClient")
    @Bean
    public WebClient configClient(){
        return WebClient.builder().baseUrl(stockUrl).build();
    }


}
