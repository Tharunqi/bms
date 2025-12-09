package com.bookmyshow.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    private Long id;
    private String title;
    private String genre;
    private int durationInMinutes;

    public Movie() {}

    public Movie(Long id, String title, String genre, int durationInMinutes) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getDetails() {
        return String.format(
                "Movie: ID=%d, Title=%s, Genre=%s, Duration=%d mins",
                this.id, this.title, this.genre, this.durationInMinutes
        );
    }
}
