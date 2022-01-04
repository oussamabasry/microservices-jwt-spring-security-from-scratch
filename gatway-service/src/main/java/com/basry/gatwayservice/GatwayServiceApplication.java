package com.basry.gatwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatwayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatwayServiceApplication.class, args);
    }

   // @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return  builder.routes()
                .route((r)-> (Buildable<Route>) r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route((r)-> (Buildable<Route>) r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties locatorProperties){
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient,locatorProperties);
    }
}
