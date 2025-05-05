package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Movies;
import com.example.demo.repo.MoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MoviesRepo moviesRepo;

    @Override
    public Movies addMovie(Movies movie) throws GlobalException {
        if (moviesRepo.existsByTitle(movie.getTitle())) {
            throw new GlobalException("Movie with this title already exists.");
        }
        return moviesRepo.save(movie);
    }

    @Override
    public Movies updateMovie(Integer movieId, Movies movieDetails) throws GlobalException {
        Movies movie = moviesRepo.findById(movieId)
                .orElseThrow(() -> new GlobalException("Movie not found with id: " + movieId));
        movie.setTitle(movieDetails.getTitle());
        movie.setDescription(movieDetails.getDescription());
        movie.setDuration(movieDetails.getDuration());
        movie.setLanguage(movieDetails.getLanguage());
        movie.setType(movieDetails.getType());
        movie.setImage(movieDetails.getImage());
        movie.setRating(movieDetails.getRating());
        movie.setTrailer(movieDetails.getTrailer());
        return moviesRepo.save(movie);
    }

    @Override
    public void deleteMovie(Integer movieId) throws GlobalException {
        if (!moviesRepo.existsById(movieId)) {
            throw new GlobalException("Movie not found with id: " + movieId);
        }
        moviesRepo.deleteById(movieId);
    }

    @Override
    public Movies getMovieById(Integer movieId) throws GlobalException {
        return moviesRepo.findById(movieId)
                .orElseThrow(() -> new GlobalException("Movie not found with id: " + movieId));
    }

    @Override
    public List<Movies> getAllMovies() {
        return moviesRepo.findAll();
    }

    @Override
    public List<Movies> searchMoviesByTitle(String title) {
        return moviesRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Movies> getMoviesByLanguage(String language) {
        return moviesRepo.findByLanguage(language);
    }
}
