package com.example.demo.repo;

import com.example.demo.modal.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Integer> {
    
    List<Seat> findByMovieShow_ShowIdAndIsBookedFalse(Integer showId);
    List<Seat> findByScreen_ScreenId(Integer screenId);
    
    // Simplified query using JPQL
    @Query("SELECT s FROM Seat s WHERE s.movieShow.showId = :showid")
    List<Seat> findByShowId(@Param("showid") Integer showid);

    @Transactional
    @Modifying
    @Query("UPDATE Seat s SET s.isBooked = true WHERE s.seatId = :seatid")
    void markSeatAsBooked(@Param("seatid") Integer seatId);
    
}
