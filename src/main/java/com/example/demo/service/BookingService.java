package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Booking;
import com.example.demo.modal.MovieShow;
import com.example.demo.modal.Seat;
import com.example.demo.modal.User;
import com.example.demo.repo.BookingRepository;
import com.example.demo.repo.MovieShowRepo;
import com.example.demo.repo.SeatRepo;
import com.example.demo.repo.UserRepo;

@Service
public class BookingService {
 private final BookingRepository bookingRepository;
 private final UserRepo userRepository;
 private final MovieShowRepo showRepository;
 private final SeatRepo seatRepository;

 public BookingService(BookingRepository bookingRepository, 
                      UserRepo userRepository,
                      MovieShowRepo showRepository,
                      SeatRepo seatRepository) {
     this.bookingRepository = bookingRepository;
     this.userRepository = userRepository;
     this.showRepository = showRepository;
     this.seatRepository = seatRepository;
 }

 @Transactional
 public Booking createBooking(Booking book) throws GlobalException {
     User user = userRepository.findById(book.getUser().getUserId())
             .orElseThrow(() -> new GlobalException("User not found"));
     
     MovieShow show = showRepository.findById(book.getShow().getShowId())
             .orElseThrow(() -> new GlobalException("Show not found"));
     
     Seat seat = seatRepository.findById(book.getSeat().getSeatId())
             .orElseThrow(() -> new GlobalException("Seat not found"));

     // Check if seat is already booked
     if (bookingRepository.existsBySeatSeatIdAndShowShowId(seat.getSeatId(), show.getShowId())) {
         throw new GlobalException("Seat already booked for this show");
     }

     Booking booking = new Booking();
     booking.setUser(user);
     booking.setShow(show);
     booking.setSeat(seat);
     booking.setBookingTime(LocalDateTime.now());
     booking.setStatus("CONFIRMED");
     booking.setPaymentStatus("PAID");

     return bookingRepository.save(booking);
 }

 public List<Booking> getBookingsByUser(Long userId) {
     return bookingRepository.findByUserUserId(userId);
 }

 public List<Booking> getBookingsByShow(Long showId) {
     return bookingRepository.findByShowShowId(showId);
 }

 public void cancelBooking(Long bookingId) throws GlobalException {
     Booking booking = bookingRepository.findById(bookingId)
             .orElseThrow(() -> new GlobalException("Booking not found"));
     booking.setStatus("CANCELLED");
     bookingRepository.save(booking);
 }
 public List<Booking> getAllBookings() {
	    return bookingRepository.findAll();
	}

}
