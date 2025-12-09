package com.bookmyshow.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seat {

    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private boolean booked;

    @ManyToOne
    @JsonIgnore
    private Show show;

    public Seat() {
    }

    public Seat(Long id, String seatNumber, boolean booked, Show show) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.booked = booked;
        this.show = show;
    }

    public Long getId() {
        return this.id;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public boolean isBooked() {
        return this.booked;
    }

    public Show getShow() {
        return this.show;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @JsonIgnore
    public String getDetails() {
        return String.format(
                "Seat: ID=%d, Number=%s, Booked=%s, ShowID=%d",
                this.id,
                this.seatNumber,
                this.booked,
                this.show != null ? this.show.getId() : null);
    }
}
