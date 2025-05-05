package com.example.demo.modal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieid")
    private Integer movieId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 800, nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duration;

    @Column(length = 50)
    private String language;

    @Column(length = 200)
    private String type;

    @Column(length = 500)
    private String image;

    @Column
    private Double rating;

    @Column(length = 300)
    private String trailer;

    // Constructors
    public Movies() {}

    public Movies(String title, String description, LocalTime duration, String language, String type,
                  String image, Double rating, String trailer) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.type = type;
        this.image = image;
        this.rating = rating;
        this.trailer = trailer;
    }

    // Getters and Setters
    public Integer getMovieId() { return movieId; }
    public void setMovieId(Integer movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalTime getDuration() { return duration; }
    public void setDuration(LocalTime duration) { this.duration = duration; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getTrailer() { return trailer; }
    public void setTrailer(String trailer) { this.trailer = trailer; }

    @Override
    public String toString() {
        return "Movies [movieId=" + movieId + ", title=" + title + ", description=" + description + ", duration="
                + duration + ", language=" + language + ", type=" + type + ", image=" + image + ", rating=" + rating
                + ", trailer=" + trailer + "]";
    }
}
