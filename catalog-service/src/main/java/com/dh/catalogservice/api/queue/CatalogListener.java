package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.model.Movie;
import com.dh.catalogservice.api.model.Serie;
import com.dh.catalogservice.api.service.CatalogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class CatalogListener {

    private final CatalogService service;

    public CatalogListener(CatalogService service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMovie(@Payload Movie movie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.saveMovie(movie);
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveSerie(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.saveSerie(serie);
    }
}
