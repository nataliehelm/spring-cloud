package com.dh.catalogservice.api.client;

import com.dh.catalogservice.api.configuration.LoadBalancerConfiguration;
import com.dh.catalogservice.api.model.Serie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "serie-service")
@LoadBalancerClient(value = "serie-service" , configuration = LoadBalancerConfiguration.CustomLoadBalancerConfiguration.class)
public interface ISerieClient {

    @GetMapping("/series/{genre}")
    List<Serie> findByGenre (@RequestParam String genre);
}
