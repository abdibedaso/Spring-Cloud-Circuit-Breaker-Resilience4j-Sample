package com.geekcolasb.development.serviceone;

import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceOneController {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/text")

    public String getText() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        String service2Text = circuitBreaker.run(() ->
                restTemplate.getForObject("http://localhost:9091/text", String.class),
                throwable -> fallback());

        return "Hello "+ service2Text;
    }

    private String fallback(){
        return "Fallback World";
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
