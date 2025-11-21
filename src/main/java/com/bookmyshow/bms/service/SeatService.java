package com.bookmyshow.bms.service;

import com.bookmyshow.bms.model.Seat;
import com.bookmyshow.bms.model.Show;
import com.bookmyshow.bms.repository.SeatRepository;
import com.bookmyshow.bms.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public SeatService(SeatRepository seatRepository, ShowRepository showRepository) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public void generateSeatsForShow(Show show) {
        List<Seat> seats = new ArrayList<>();
        long idCounter = 1;

        for (int i = 1; i <= 30; i++) {
            String seatNum = "A" + i;
            Seat seat = new Seat(idCounter++, seatNum, false, show);
            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }

    public List<Seat> getSeatsForShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }
}
