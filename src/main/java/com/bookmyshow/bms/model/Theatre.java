package com.bookmyshow.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Theatre{

    @Id
    private Long id;
    private String name;
    private String city;

    public Theatre() {}

    public Theatre(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return String.format(
                "Theatre: ID=%d, Name=%s, City=%s",
                this.id, this.name, this.city
        );
    }
}
