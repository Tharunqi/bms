package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.Booking;
import com.bookmyshow.bms.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(
            @RequestParam Long showId,
            @RequestParam Long bookingId,
            @RequestParam String userName,
            @RequestParam List<String> seats
    ) {
        return bookingService.createBooking(showId, seats, userName, bookingId);
    }
}

