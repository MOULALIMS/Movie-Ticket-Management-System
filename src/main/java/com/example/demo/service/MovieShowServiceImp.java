package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.MovieShow;
import com.example.demo.modal.Movies;
import com.example.demo.modal.Screen;
import com.example.demo.repo.MovieShowRepo;
import com.example.demo.repo.MoviesRepo;
import com.example.demo.repo.ScreenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class MovieShowServiceImp implements MovieShowService {

    @Autowired
    private MovieShowRepo movieShowRepo;

    @Autowired
    private MoviesRepo moviesRepo;

    @Autowired
    private ScreenRepo screenRepo;

    @Override
    public MovieShow addShow(
            Integer movieId,
            Integer screenId,
            LocalDate date,
            LocalTime startTime,
            Double price
        ) throws GlobalException {
            Movies movie = moviesRepo.findById(movieId)
                .orElseThrow(() -> new GlobalException("Movie not found"));
                
            Screen screen = screenRepo.findById(screenId)
                .orElseThrow(() -> new GlobalException("Screen not found"));

            MovieShow show = new MovieShow();
            show.setMovie(movie);
            show.setScreen(screen);
            show.setDate(date);
            show.setStartTime(startTime);
            show.setPrice(price);

            return movieShowRepo.save(show);
        }
    @Override
    public MovieShow addShow(MovieShow show, Integer movieId, Integer screenId) throws GlobalException {
        Movies movie = moviesRepo.findById(movieId)
                .orElseThrow(() -> new GlobalException("Movie not found with id: " + movieId));
        Screen screen = screenRepo.findById(screenId)
                .orElseThrow(() -> new GlobalException("Screen not found with id: " + screenId));
        show.setMovie(movie);
        show.setScreen(screen);
        return movieShowRepo.save(show);
    }

    @Override
    public MovieShow updateShow(Integer showId, MovieShow showDetails) throws GlobalException {
        MovieShow show = movieShowRepo.findById(showId)
                .orElseThrow(() -> new GlobalException("Show not found with id: " + showId));
        show.setDate(showDetails.getDate());
        show.setStartTime(showDetails.getStartTime());
        show.setPrice(showDetails.getPrice());
        // Optionally update movie/screen if IDs provided
        if (showDetails.getMovie() != null) {
            show.setMovie(showDetails.getMovie());
        }
        if (showDetails.getScreen() != null) {
            show.setScreen(showDetails.getScreen());
        }
        return movieShowRepo.save(show);
    }

    @Override
    public void deleteShow(Integer showId) throws GlobalException {
        if (!movieShowRepo.existsById(showId)) {
            throw new GlobalException("Show not found with id: " + showId);
        }
        movieShowRepo.deleteById(showId);
    }

    @Override
    public MovieShow getShowById(Integer showId) throws GlobalException {
        return movieShowRepo.findById(showId)
                .orElseThrow(() -> new GlobalException("Show not found with id: " + showId));
    }

    @Override
    public List<MovieShow> getShowsByMovie(Integer movieId) throws GlobalException {
        if (!moviesRepo.existsById(movieId)) {
            throw new GlobalException("Movie not found with id: " + movieId);
        }
        return movieShowRepo.findByMovie_MovieId(movieId);
    }

    @Override
    public List<MovieShow> getShowsByScreenAndDate(Integer screenId, Date date) throws GlobalException {
        if (!screenRepo.existsById(screenId)) {
            throw new GlobalException("Screen not found with id: " + screenId);
        }
        return movieShowRepo.findByDateAndScreen_ScreenId(date, screenId);
    }

    @Override
    public List<MovieShow> getAllShows() {
        return movieShowRepo.findAll();
    }
}
