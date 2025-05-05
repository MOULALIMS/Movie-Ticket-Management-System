package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.MovieShow;

public interface MovieShowService {

	MovieShow addShow(MovieShow show, Integer movieId, Integer screenId) throws GlobalException;

	MovieShow updateShow(Integer showId, MovieShow showDetails) throws GlobalException;

	void deleteShow(Integer showId) throws GlobalException;

	MovieShow getShowById(Integer showId) throws GlobalException;

	List<MovieShow> getShowsByMovie(Integer movieId) throws GlobalException;

	List<MovieShow> getShowsByScreenAndDate(Integer screenId, Date date) throws GlobalException;

	List<MovieShow> getAllShows();

	MovieShow addShow(Integer movieId, Integer screenId, LocalDate date, LocalTime startTime, Double price) throws GlobalException;

}
