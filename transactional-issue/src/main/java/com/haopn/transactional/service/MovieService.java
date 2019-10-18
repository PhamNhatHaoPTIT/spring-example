package com.haopn.transactional.service;

import com.haopn.transactional.model.Movie;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class MovieService {

    @PersistenceContext
    EntityManager entityManager;

    public Movie getMovie(int movieId) {
        Movie movie = entityManager.find(Movie.class, movieId);
        return movie;
    }
}
