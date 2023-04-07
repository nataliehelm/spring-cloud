package com.dh.catalogservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Catalog {

    private String genre;
    private List<Movie> movies = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();


}
