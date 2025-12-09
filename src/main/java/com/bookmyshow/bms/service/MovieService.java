package com.bookmyshow.bms.service;

import com.bookmyshow.bms.model.Movie;
import com.bookmyshow.bms.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        if (movie.getId() != null && movieRepository.existsById(movie.getId())) {
            throw new com.bookmyshow.bms.exception.DuplicateIdException(
                    "Movie with ID " + movie.getId() + " already exists.");
        }
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
