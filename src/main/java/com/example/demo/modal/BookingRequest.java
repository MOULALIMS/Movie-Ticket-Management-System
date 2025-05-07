package com.example.demo.modal;

public class BookingRequest {
    private Long userId;
    private Long showId;
    private Long seatId;
    
	public BookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingRequest(Long userId, Long showId, Long seatId) {
		super();
		this.userId = userId;
		this.showId = showId;
		this.seatId = seatId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}
    
    
}