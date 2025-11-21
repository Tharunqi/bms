package com.bookmyshow.bms.repository;

import com.bookmyshow.bms.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
