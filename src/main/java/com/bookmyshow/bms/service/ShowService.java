package com.bookmyshow.bms.service;

import com.bookmyshow.bms.model.Movie;
import com.bookmyshow.bms.model.Show;
import com.bookmyshow.bms.model.Theatre;
import com.bookmyshow.bms.repository.MovieRepository;
import com.bookmyshow.bms.repository.ShowRepository;
import com.bookmyshow.bms.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    public List<Show> getShowsForMovie(Long movieId) {
        return showRepository.findAll()
                .stream()
                .filter(s -> s.getMovie().getId().equals(movieId))
                .toList();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }
}
