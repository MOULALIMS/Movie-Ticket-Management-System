package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.City;
import com.example.demo.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public City addCity(City city) throws GlobalException {
        if (cityRepo.existsByCityname(city.getCityname())) {
            throw new GlobalException("City with this name already exists.");
        }
        return cityRepo.save(city);
    }

    @Override
    public City updateCity(Integer zipcode, City cityDetails) throws GlobalException {
        City city = cityRepo.findById(zipcode)
                .orElseThrow(() -> new GlobalException("City not found with zipcode: " + zipcode));
        city.setCityname(cityDetails.getCityname());
        city.setState(cityDetails.getState());
        return cityRepo.save(city);
    }

    @Override
    public void deleteCity(Integer zipcode) throws GlobalException {
        if (!cityRepo.existsById(zipcode)) {
            throw new GlobalException("City not found with zipcode: " + zipcode);
        }
        cityRepo.deleteById(zipcode);
    }

    @Override
    public City getCityByZipcode(Integer zipcode) throws GlobalException {
        return cityRepo.findById(zipcode)
                .orElseThrow(() -> new GlobalException("City not found with zipcode: " + zipcode));
    }

    @Override
    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    @Override
    public City getCityByName(String cityname) throws GlobalException {
        return cityRepo.findByCityname(cityname)
                .orElseThrow(() -> new GlobalException("City not found with name: " + cityname));
    }

    @Override
    public List<City> getCitiesByState(String state) {
        return cityRepo.findByState(state);
    }
}
