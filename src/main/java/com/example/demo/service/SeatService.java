package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Seat;

public interface SeatService {

	List<Seat> getSeatsByShow(Integer showId) throws GlobalException;

	List<Seat> getAvailableSeatsByShow(Integer showId) throws GlobalException;

	Seat bookSeat(Integer seatId) throws GlobalException;

	Seat cancelSeatBooking(Integer seatId) throws GlobalException;

	void addSeatsForShow(Integer screenId, Integer showId, int totalSeats) throws GlobalException;

	void addSeats(Integer screenId, Integer integer) throws GlobalException;

	void bookSeats(List<Integer> seatIds);

}
