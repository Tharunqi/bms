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

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository,
            TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public Show addShow(Show show) {
        // Force the ID to be null to ensure a new record is created.
        // This handles cases where the user accidentally sends an ID in the POST body
        show.setId(null);
        return showRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<Show> getShowsForMovie(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }

    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 60000) // Check every minute
    @org.springframework.transaction.annotation.Transactional
    public void removeExpiredShows() {
        showRepository.deleteByShowTimeBefore(java.time.LocalDateTime.now());
        // Also cleanup corrupted data
        showRepository.deleteByMovieIsNull();
        showRepository.deleteByTheatreIsNull();
    }
}
