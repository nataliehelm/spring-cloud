package com.dh.catalogservice.api.service;


import com.dh.catalogservice.api.client.IMovieClient;
import com.dh.catalogservice.api.client.ISerieClient;
import com.dh.catalogservice.api.model.Movie;
import com.dh.catalogservice.api.model.Serie;
import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.api.repository.ISerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogService {

    private ISerieClient iSerieClient;
    private IMovieClient iMovieClient;
    private IMovieRepository iMovieRepository;
    private ISerieRepository iSerieRepository;

    public CatalogService(ISerieClient iSerieClient, IMovieClient iMovieClient, IMovieRepository iMovieRepository, ISerieRepository iSerieRepository) {
        this.iSerieClient = iSerieClient;
        this.iMovieClient = iMovieClient;
        this.iMovieRepository = iMovieRepository;
        this.iSerieRepository = iSerieRepository;
    }
    public List<Movie> findMoviesByGenre (String genre){
        List<Movie> movies = iMovieClient.findByGenre(genre);
        return movies;
    }
    // Se configura circuit breaker en el MS de series debido a que tiene más demanda que el de películas.
    // Si el MS falla, se abrirá el circuit breaker y catalog traerá las series de su propia base de datos
    @CircuitBreaker(name="series",fallbackMethod = "seriesFallbackMethod")
    @Retry(name = "series")
    public List<Serie> findSeriesByGenre(String genre){
        System.out.println("Ejecutando...");
        List<Serie> series = iSerieClient.findByGenre(genre);
        return series;
    }

    private List<Serie> seriesFallbackMethod(String genre , CallNotPermittedException exception){
        List<Serie> series = iSerieRepository.findByGenre(genre);
        return series;
    }

    public Movie saveMovie (Movie movie){
        return iMovieRepository.insert(movie);
    }

    public Serie saveSerie (Serie serie){
        return iSerieRepository.insert(serie);
    }

    public List<Movie> getMovies (String genre){
        return iMovieRepository.findByGenre(genre);
    }

    public List<Serie> getSeries (String genre){
        return iSerieRepository.findByGenre(genre);
    }

}
