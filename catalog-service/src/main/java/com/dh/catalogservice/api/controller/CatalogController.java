package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.model.Catalog;
import com.dh.catalogservice.api.model.Movie;
import com.dh.catalogservice.api.model.Serie;
import com.dh.catalogservice.api.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {


    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping("/{genre}")
    ResponseEntity<Catalog> getAllByGenre(@PathVariable String genre){
        Catalog catalog = new Catalog();
        catalog.setGenre(genre);
        List<Movie> movies = catalogService.findMoviesByGenre(genre);
        catalog.setMovies(movies);
        List<Serie> series = catalogService.findSeriesByGenre(genre);
        catalog.setSeries(series);
        return ResponseEntity.ok().body(catalog);
    }

    @GetMapping("/offline/{genre}")
    ResponseEntity<Catalog> getByGenre(@PathVariable String genre){
        Catalog catalog = new Catalog();
        catalog.setGenre(genre);
        List<Movie> movies = catalogService.getMovies(genre);
        catalog.setMovies(movies);
        List<Serie> series = catalogService.getSeries(genre);
        catalog.setSeries(series);
        return ResponseEntity.ok().body(catalog);
    }


}
