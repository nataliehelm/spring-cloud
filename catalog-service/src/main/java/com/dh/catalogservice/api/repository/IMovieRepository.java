package com.dh.catalogservice.api.repository;


import com.dh.catalogservice.api.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IMovieRepository extends MongoRepository<Movie, Long> {

    List<Movie> findByGenre (String genre);

}
