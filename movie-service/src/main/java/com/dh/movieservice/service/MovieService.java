package com.dh.movieservice.service;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vaninagodoy
 */

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieSender sender;

    public MovieService(MovieRepository movieRepository, MovieSender sender) {
        this.movieRepository = movieRepository;
        this.sender = sender;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        Movie response = movieRepository.save(movie);
        sender.send(movie);
        return response;
    }
}
