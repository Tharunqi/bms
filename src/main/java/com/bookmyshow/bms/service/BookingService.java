package com.bookmyshow.bms.service;

import com.bookmyshow.bms.model.Booking;
import com.bookmyshow.bms.model.Seat;
import com.bookmyshow.bms.model.Show;
import com.bookmyshow.bms.repository.BookingRepository;
import com.bookmyshow.bms.repository.SeatRepository;
import com.bookmyshow.bms.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public Booking createBooking(Long showId, List<String> seats, String userName, Long bookingId) {

        Show show = showRepository.findById(showId).orElse(null);
        if (show == null) return null;

        List<Seat> seatList = seatRepository.findByShowId(showId);

        // Check if seats are free
        for (Seat s : seatList) {
            if (seats.contains(s.getSeatNumber()) && s.isBooked()) {
                return null; // seat already taken
            }
        }

        // Mark selected seats booked
        for (Seat s : seatList) {
            if (seats.contains(s.getSeatNumber())) {
                s.setBooked(true);
                seatRepository.save(s);
            }
        }

        // Save booking
        String joinedSeats = String.join(",", seats);

        Booking booking = new Booking(
                bookingId,
                userName,
                joinedSeats,
                show
        );

        return bookingRepository.save(booking);
    }
}
