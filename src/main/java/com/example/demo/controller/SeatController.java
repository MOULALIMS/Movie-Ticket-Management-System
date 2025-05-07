package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Seat;
import com.example.demo.service.SeatService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;
    
    @GetMapping("/show/{showId}/available")
    public List<Seat> getAvailableSeats1(@PathVariable Integer showId) throws GlobalException {
        return seatService.getAvailableSeatsByShow(showId);
    }

    @PostMapping("/book")
    public ResponseEntity<Void> bookSeats(@RequestBody List<Integer> seatIds) {
        seatService.bookSeats(seatIds);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/add/{screenId}")
    public ResponseEntity<Void> addSeats(
        @PathVariable Integer screenId,
        @RequestBody Map<String, Integer> request
    ) throws GlobalException {
        seatService.addSeats(screenId, request.get("numberOfSeats"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Integer showId) throws GlobalException {
        return ResponseEntity.ok(seatService.getAvailableSeatsByShow(showId));
    }

    @PostMapping("/book/{seatId}")
    public ResponseEntity<Seat> bookSeat(@PathVariable Integer seatId) throws GlobalException {
        return ResponseEntity.ok(seatService.bookSeat(seatId));
    }
    
    @PostMapping("/cancel/{seatId}")
    public ResponseEntity<Seat> cancelSeat(@PathVariable Integer seatId) throws GlobalException {
        return ResponseEntity.ok(seatService.cancelSeatBooking(seatId));
    }
}

