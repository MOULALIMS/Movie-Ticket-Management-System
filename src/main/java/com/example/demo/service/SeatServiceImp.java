package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Seat;
import com.example.demo.modal.MovieShow;
import com.example.demo.modal.Screen;
import com.example.demo.repo.SeatRepo;
import com.example.demo.repo.MovieShowRepo;
import com.example.demo.repo.ScreenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class SeatServiceImp implements SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private MovieShowRepo movieShowRepo;

    @Autowired
    private ScreenRepo screenRepo;

    // Get all seats for a show
    @Override
    public List<Seat> getSeatsByShow(Integer showId) throws GlobalException {
        MovieShow show = movieShowRepo.findById(showId)
                .orElseThrow(() -> new GlobalException("Show not found with id: " + showId));
        return seatRepo.findByShowId(show.getShowId());
    }
    // Book a seat for a show
    @Override
    public Seat bookSeat(Integer seatId) throws GlobalException {
        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() -> new GlobalException("Seat not found with id: " + seatId));
        if (seat.getIsBooked() != null && seat.getIsBooked()) {
            throw new GlobalException("Seat already booked");
        }
        seat.setIsBooked(true);
        return seatRepo.save(seat);
    }

    // Cancel a booked seat
    @Override
    public Seat cancelSeatBooking(Integer seatId) throws GlobalException {
        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() -> new GlobalException("Seat not found with id: " + seatId));
        if (seat.getIsBooked() == null || !seat.getIsBooked()) {
            throw new GlobalException("Seat is not booked");
        }
        seat.setIsBooked(false);
        return seatRepo.save(seat);
    }

    // Add seats for a screen and show (admin functionality)
    @Override
    public void addSeatsForShow(Integer screenId, Integer showId, int totalSeats) throws GlobalException {
        Screen screen = screenRepo.findById(screenId)
                .orElseThrow(() -> new GlobalException("Screen not found with id: " + screenId));
        MovieShow show = movieShowRepo.findById(showId)
                .orElseThrow(() -> new GlobalException("Show not found with id: " + showId));
        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setIsBooked(false);
            seat.setScreen(screen);
            seat.setMovieShow(show);
            seatRepo.save(seat);
        }
    }

    @Override
    public void addSeats(Integer screenId, Integer numberOfSeats) throws GlobalException {
        Screen screen = screenRepo.findById(screenId)
            .orElseThrow(() -> new GlobalException("Screen not found"));
        
        List<Seat> seats = new ArrayList<>();
        
        int currentSeats = screen.getTotalNoOfSeats();
        
        for(int i = 1; i <= numberOfSeats; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(currentSeats + i);
            seat.setScreen(screen);
            seat.setIsBooked(false);
            seats.add(seat);
        }
        
        seatRepo.saveAll(seats);
        
        // Update total seats count in screen
        screen.setTotalNoOfSeats(currentSeats + numberOfSeats);
        screenRepo.save(screen);
    }
    
    @Override
    public List<Seat> getAvailableSeatsByShow(Integer showId) {
        return seatRepo.findByMovieShow_ShowIdAndIsBookedFalse(showId);
    }

    @Transactional
    public void bookSeats(List<Integer> seatIds) {
        seatIds.forEach(id -> {
            Seat seat = seatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
            seat.setIsBooked(true);
            seatRepo.save(seat);
        });
    }

}
