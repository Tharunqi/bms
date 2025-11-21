package com.bookmyshow.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Booking {

    @Id
    private Long id;
    private String userName;
    private String seatNumbers; // e.g. "A1,A2,A3"

    @ManyToOne
    private Show show;

    public Booking() {}

    public Booking(Long id, String userName, String seatNumbers, Show show) {
        this.id = id;
        this.userName = userName;
        this.seatNumbers = seatNumbers;
        this.show = show;
    }

    public Long getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSeatNumbers() {
        return this.seatNumbers;
    }

    public Show getShow() {
        return this.show;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getDetails() {
        return String.format(
                "Booking: ID=%d, User=%s, Seats=%s, ShowID=%d",
                this.id,
                this.userName,
                this.seatNumbers,
                this.show != null ? this.show.getId() : null
        );
    }
}

