package com.example.helloclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloClientApplication {



	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}
}

@RestController
@RequestMapping("/rest/hello/client")
class HelloResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String hello() {
        String url = "http://localhost:8071/rest/hello/server";
        return restTemplate.getForObject(url, String.class);
    }
}

@Configuration
class Config {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
