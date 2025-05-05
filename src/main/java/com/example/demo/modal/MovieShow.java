package com.example.demo.modal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "movieshow")
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showid")
    private Integer showId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "starttime", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "screenid", referencedColumnName = "screenid")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "movieid", referencedColumnName = "movieid")
    private Movies movie;

    // Constructors
    public MovieShow() {}

    public MovieShow(LocalDate date, LocalTime startTime, Double price, Screen screen, Movies movie) {
        this.date = date;
        this.startTime = startTime;
        this.price = price;
        this.screen = screen;
        this.movie = movie;
    }

    // Getters and Setters
    public Integer getShowId() { return showId; }
    public void setShowId(Integer showId) { this.showId = showId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Screen getScreen() { return screen; }
    public void setScreen(Screen screen) { this.screen = screen; }

    public Movies getMovie() { return movie; }
    public void setMovie(Movies movie) { this.movie = movie; }

    @Override
    public String toString() {
        return "MovieShow [showId=" + showId + ", date=" + date + ", startTime=" + startTime + ", price=" + price
                + ", screen=" + screen + ", movie=" + movie + "]";
    }
}
