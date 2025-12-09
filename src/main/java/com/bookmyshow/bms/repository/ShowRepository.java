package com.bookmyshow.bms.repository;

import com.bookmyshow.bms.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
    java.util.List<Show> findByMovieId(Long movieId);

    void deleteByShowTimeBefore(java.time.LocalDateTime dateTime);

    void deleteByMovieIsNull();

    void deleteByTheatreIsNull();
}
