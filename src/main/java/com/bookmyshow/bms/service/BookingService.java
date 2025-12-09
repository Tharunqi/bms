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

    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository,
            ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public Booking createBooking(Long showId, List<String> seats, String userName, Long bookingId) {

        // 1. Validate Show
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show with ID " + showId + " not found."));

        // 2. Fetch required seats
        List<Seat> allSeats = seatRepository.findByShowId(showId);
        List<Seat> targetSeats = new java.util.ArrayList<>();

        for (String seatNum : seats) {
            Seat found = allSeats.stream()
                    .filter(s -> s.getSeatNumber().equals(seatNum))
                    .findFirst()
                    .orElseThrow(() -> new com.bookmyshow.bms.exception.InvalidSeatException(
                            "Seat number " + seatNum + " does not exist for this show."));
            targetSeats.add(found);
        }

        // 3. Check availability
        for (Seat s : targetSeats) {
            if (s.isBooked()) {
                throw new com.bookmyshow.bms.exception.SeatAlreadyBookedException(
                        "Seat " + s.getSeatNumber() + " is already booked.");
            }
        }

        // 4. Mark booked and save
        for (Seat s : targetSeats) {
            s.setBooked(true);
            seatRepository.save(s);
        }

        // 5. Create Booking
        String joinedSeats = String.join(",", seats);

        Booking booking = new Booking(
                bookingId,
                userName,
                joinedSeats,
                show);

        return bookingRepository.save(booking);
    }
}
