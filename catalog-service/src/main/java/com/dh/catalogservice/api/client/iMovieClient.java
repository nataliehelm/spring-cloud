package com.dh.catalogservice.api.client;


import com.dh.catalogservice.domain.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient("movie-service")
public interface iMovieClient {

    @GetMapping("/movies/{genre}")
    List<Movie> genre(String genre);
}
