package com.pandi.reactive.aggrigator.service.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomerWebClientConfig {

    @Value("${customer.service.url}")
    private String customerHostUrl;

    @Qualifier("customerWebClient")
    @Bean
    public WebClient customerClientConfig(){

       return WebClient.builder().baseUrl(customerHostUrl).build();
    }

}
