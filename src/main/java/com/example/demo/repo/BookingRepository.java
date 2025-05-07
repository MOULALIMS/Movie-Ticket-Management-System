package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
 List<Booking> findByUserUserId(Long userId);
 List<Booking> findByShowShowId(Long showId);
 boolean existsBySeatSeatIdAndShowShowId(Integer integer, Integer integer2);
}
