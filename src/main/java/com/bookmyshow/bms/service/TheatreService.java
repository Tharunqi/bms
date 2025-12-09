package com.bookmyshow.bms.service;

import com.bookmyshow.bms.model.Theatre;
import com.bookmyshow.bms.repository.TheatreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre addTheatre(Theatre theatre) {
        if (theatre.getId() != null && theatreRepository.existsById(theatre.getId())) {
            throw new com.bookmyshow.bms.exception.DuplicateIdException(
                    "Theatre with ID " + theatre.getId() + " already exists.");
        }
        return theatreRepository.save(theatre);
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }
}
