package com.dh.catalogservice.api.client;

import com.dh.catalogservice.api.configuration.LoadBalancerConfiguration;
import com.dh.catalogservice.domain.model.Movie;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "movie-service")
@LoadBalancerClient(value = "movie-service" , configuration = LoadBalancerConfiguration.CustomLoadBalancerConfiguration.class)
public interface IMovieClient {

    @GetMapping("/movies/{genre}")
    List<Movie> findByGenre (@RequestParam String genre);

}
