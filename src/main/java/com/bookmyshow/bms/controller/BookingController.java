package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.Booking;
import com.bookmyshow.bms.service.BookingService;
import com.bookmyshow.bms.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final EmailService emailService;

    public BookingController(BookingService bookingService, EmailService emailService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
    }

    @PostMapping
    public Booking createBooking(
            @RequestParam Long showId,
            @RequestParam Long bookingId,
            @RequestParam String userName,
            @RequestParam List<String> seats,
            @RequestParam String clientEmail) {
        // 1. Execute core booking logic
        Booking booking = bookingService.createBooking(showId, seats, userName, bookingId);

        emailService.sendBookingConfirmation(clientEmail, booking);

        return booking;
    }
}