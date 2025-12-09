package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.Movie;
import com.bookmyshow.bms.model.Show;
import com.bookmyshow.bms.model.Theatre;
import com.bookmyshow.bms.service.MovieService;
import com.bookmyshow.bms.service.SeatService;
import com.bookmyshow.bms.service.ShowService;
import com.bookmyshow.bms.service.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    private final SeatService seatService;
    private final MovieService movieService;
    private final TheatreService theatreService;

    public ShowController(
            ShowService showService,
            SeatService seatService,
            MovieService movieService,
            TheatreService theatreService) {
        this.showService = showService;
        this.seatService = seatService;
        this.movieService = movieService;
        this.theatreService = theatreService;
    }

    @PostMapping
    public Show addShow(@RequestBody Show show) {

        // Fetch complete Movie object
        Movie movie = movieService.getMovieById(show.getMovie().getId());
        if (movie == null) {
            throw new RuntimeException("Movie with ID " + show.getMovie().getId() + " not found.");
        }
        show.setMovie(movie);

        // Fetch complete Theatre object
        Theatre theatre = theatreService.getTheatreById(show.getTheatre().getId());
        if (theatre == null) {
            throw new RuntimeException("Theatre with ID " + show.getTheatre().getId() + " not found.");
        }
        show.setTheatre(theatre);

        // Save show
        Show saved = showService.addShow(show);

        // Auto-generate seats
        seatService.generateSeatsForShow(saved);

        return saved;
    }

    @GetMapping("/movie/{movieId}")
    public List<Show> getShows(@PathVariable Long movieId) {
        return showService.getShowsForMovie(movieId);
    }

    @GetMapping("/{id}")
    public Show getShow(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }
}
