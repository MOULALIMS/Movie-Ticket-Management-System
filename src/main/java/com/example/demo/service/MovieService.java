package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Movies;

public interface MovieService {

	Movies addMovie(Movies movie) throws GlobalException;

	Movies updateMovie(Integer movieId, Movies movieDetails) throws GlobalException;

	void deleteMovie(Integer movieId) throws GlobalException;

	Movies getMovieById(Integer movieId) throws GlobalException;

	List<Movies> getAllMovies();

	List<Movies> searchMoviesByTitle(String title);

	List<Movies> getMoviesByLanguage(String language);
	
}