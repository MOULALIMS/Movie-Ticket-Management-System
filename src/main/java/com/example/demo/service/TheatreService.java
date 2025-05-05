package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Theatre;

public interface TheatreService {

	Theatre addTheatre(Theatre theatre, Integer cityZipcode) throws GlobalException;

	Theatre updateTheatre(Integer theatreId, Theatre theatreDetails) throws GlobalException;

	Theatre getTheatreById(Integer theatreId) throws GlobalException;

	List<Theatre> getAllTheatres();

	List<Theatre> getTheatresByCity(Integer cityZipcode) throws GlobalException;

	void deleteTheatre(Integer theatreId) throws GlobalException;

}
