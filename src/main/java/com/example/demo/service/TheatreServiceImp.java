package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Theatre;
import com.example.demo.modal.City;
import com.example.demo.repo.TheatreRepo;
import com.example.demo.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TheatreServiceImp implements TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private CityRepo cityRepo;

    // Add a new theatre
    @Override
    public Theatre addTheatre(Theatre theatre, Integer cityZipcode) throws GlobalException {
        City city = cityRepo.findById(cityZipcode)
                .orElseThrow(() -> new GlobalException("City not found with zipcode: " + cityZipcode));
        theatre.setCity(city);
        return theatreRepo.save(theatre);
    }

    // Update theatre details
    @Override
    public Theatre updateTheatre(Integer theatreId, Theatre theatreDetails) throws GlobalException {
        Theatre theatre = theatreRepo.findById(theatreId)
                .orElseThrow(() -> new GlobalException("Theatre not found with id: " + theatreId));
        theatre.setTheatreName(theatreDetails.getTheatreName());
        theatre.setTotalScreens(theatreDetails.getTotalScreens());
        if (theatreDetails.getCity() != null) {
            theatre.setCity(theatreDetails.getCity());
        }
        return theatreRepo.save(theatre);
    }

    // Delete a theatre
    @Override
    public void deleteTheatre(Integer theatreId) throws GlobalException {
        if (!theatreRepo.existsById(theatreId)) {
            throw new GlobalException("Theatre not found with id: " + theatreId);
        }
        theatreRepo.deleteById(theatreId);
    }

    // Get theatre by id
    @Override
    public Theatre getTheatreById(Integer theatreId) throws GlobalException {
        return theatreRepo.findById(theatreId)
                .orElseThrow(() -> new GlobalException("Theatre not found with id: " + theatreId));
    }

    // Get all theatres
    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepo.findAll();
    }

    // Get theatres by city
    @Override
    public List<Theatre> getTheatresByCity(Integer cityZipcode) throws GlobalException {
        City city = cityRepo.findById(cityZipcode)
                .orElseThrow(() -> new GlobalException("City not found with zipcode: " + cityZipcode));
        return theatreRepo.findByCity_Cityname(city.getCityname());
    }
}
