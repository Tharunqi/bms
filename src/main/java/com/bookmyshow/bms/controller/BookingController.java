package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.Booking;
import com.bookmyshow.bms.service.BookingService;
import com.bookmyshow.bms.service.EmailService; // 🚨 NEW IMPORT
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final EmailService emailService; // 🚨 NEW DEPENDENCY

    // 🚨 UPDATE CONSTRUCTOR
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
            @RequestParam String clientEmail // 🚨 NEW PARAMETER
    ) {
        // 1. Execute core booking logic
        Booking booking = bookingService.createBooking(showId, seats, userName, bookingId);

        // 2. Send email confirmation 🚨 NEW LOGIC
        // Note: The EmailService uses placeholder movie/show details for simplicity.
        emailService.sendBookingConfirmation(clientEmail, userName, bookingId, seats);

        return booking;
    }
}