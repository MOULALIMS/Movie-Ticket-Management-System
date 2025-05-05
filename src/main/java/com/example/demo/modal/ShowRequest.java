package com.example.demo.modal;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowRequest {
	 private Integer movieId;
	 private Integer screenId;
	 private LocalDate date;
	 private LocalTime startTime;
	 private Double price;
	public ShowRequest(Integer movieId, Integer screenId, LocalDate date, LocalTime startTime, Double price) {
		super();
		this.movieId = movieId;
		this.screenId = screenId;
		this.date = date;
		this.startTime = startTime;
		this.price = price;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getScreenId() {
		return screenId;
	}
	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	 
}
