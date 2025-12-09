package com.bookmyshow.bms.model;

import jakarta.persistence.*; // Imported for @Table, @GeneratedValue, etc.
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "shows") // CHANGE 1: renaming table to avoid MySQL reserved keyword "SHOW"
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // CHANGE 2: Auto-increment ID for MySQL
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id") // Optional but good practice for MySQL foreign keys
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id") // Optional but good practice for MySQL foreign keys
    private Theatre theatre;

    private LocalDateTime showTime;
    private int price;

    public Show() {
    }

    public Show(Long id, Movie movie, Theatre theatre, LocalDateTime showTime, int price) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.showTime = showTime;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Theatre getTheatre() {
        return this.theatre;
    }

    public LocalDateTime getShowTime() {
        return this.showTime;
    }

    public int getPrice() {
        return this.price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return String.format(
                "Show: ID=%d, Movie=%s, Theatre=%s, Time=%s, Price=%d",
                this.id,
                (this.movie != null) ? this.movie.getTitle() : "N/A",
                (this.theatre != null) ? this.theatre.getName() : "N/A",
                (this.showTime != null) ? this.showTime.toString() : "N/A",
                this.price);
    }
}
