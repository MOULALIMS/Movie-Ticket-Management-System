package com.example.demo.controller;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.MovieShow;
import com.example.demo.modal.ShowRequest;
import com.example.demo.service.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/shows")
public class MovieShowController {

	@Autowired
    private MovieShowService movieShowService;


    // Admin endpoints
	@PostMapping("/admin")
    public ResponseEntity<MovieShow> scheduleShow(
            @RequestBody ShowRequest showRequest) throws GlobalException {
        MovieShow savedShow = movieShowService.addShow(
            showRequest.getMovieId(),
            showRequest.getScreenId(),
            showRequest.getDate(),
            showRequest.getStartTime(),
            showRequest.getPrice()
        );
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }

    @PutMapping("/admin/{showId}")
    public ResponseEntity<MovieShow> updateShow(
            @PathVariable Integer showId,
            @RequestBody MovieShow showDetails) throws GlobalException {
        MovieShow updatedShow = movieShowService.updateShow(showId, showDetails);
        return ResponseEntity.ok(updatedShow);
    }

    @DeleteMapping("/admin/{showId}")
    public ResponseEntity<Void> cancelShow(@PathVariable Integer showId) throws GlobalException {
        movieShowService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }

    // Public endpoints
    @GetMapping("/{showId}")
    public ResponseEntity<MovieShow> getShowById(@PathVariable Integer showId) throws GlobalException {
        return ResponseEntity.ok(movieShowService.getShowById(showId));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<MovieShow>> getShowsByMovie(@PathVariable Integer movieId) throws GlobalException {
        return ResponseEntity.ok(movieShowService.getShowsByMovie(movieId));
    }

    @GetMapping("/screen/{screenId}/date/{date}")
    public ResponseEntity<List<MovieShow>> getShowsByScreenAndDate(
            @PathVariable Integer screenId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws GlobalException {
        return ResponseEntity.ok(movieShowService.getShowsByScreenAndDate(screenId, date));
    }

    @GetMapping
    public ResponseEntity<List<MovieShow>> getAllShows() {
        return ResponseEntity.ok(movieShowService.getAllShows());
    }
}
