package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatid")
    private Integer seatId;

    @Column(name = "seatnumber", nullable = false)
    private Integer seatNumber;

    @Column(name = "isbooked", nullable = false)
    private Boolean isBooked = false;

    @ManyToOne
    @JoinColumn(name = "screenid", referencedColumnName = "screenid")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "showid", referencedColumnName = "showid")
    private MovieShow movieShow;

    // Constructors
    public Seat() {}

    public Seat(Integer seatNumber, Boolean isBooked, Screen screen, MovieShow movieShow) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
        this.screen = screen;
        this.movieShow = movieShow;
    }

    // Getters and Setters
    public Integer getSeatId() { return seatId; }
    public void setSeatId(Integer seatId) { this.seatId = seatId; }

    public Integer getSeatNumber() { return seatNumber; }
    public void setSeatNumber(Integer seatNumber) { this.seatNumber = seatNumber; }

    public Boolean getIsBooked() { return isBooked; }
    public void setIsBooked(Boolean isBooked) { this.isBooked = isBooked; }

    public Screen getScreen() { return screen; }
    public void setScreen(Screen screen) { this.screen = screen; }

    public MovieShow getMovieShow() { return movieShow; }
    public void setMovieShow(MovieShow movieShow) { this.movieShow = movieShow; }

    @Override
    public String toString() {
        return "Seat [seatId=" + seatId + ", seatNumber=" + seatNumber + ", isBooked=" + isBooked + ", screen=" + screen
                + ", movieShow=" + movieShow + "]";
    }
}
