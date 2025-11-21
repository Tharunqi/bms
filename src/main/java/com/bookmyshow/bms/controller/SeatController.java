package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.Seat;
import com.bookmyshow.bms.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/show/{showId}")
    public List<Seat> getSeats(@PathVariable Long showId) {
        return seatService.getSeatsForShow(showId);
    }
}
