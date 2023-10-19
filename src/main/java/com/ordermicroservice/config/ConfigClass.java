package com.ordermicroservice.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigClass {

//
//    @Bean
//    @LoadBalanced
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }


    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }



}
