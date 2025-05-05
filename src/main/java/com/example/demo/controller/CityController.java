package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.City;
import com.example.demo.service.CityService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("addCity")
    public ResponseEntity<City> addCity(@RequestBody City city) throws GlobalException {
        City savedCity = cityService.addCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @PutMapping("/{zipcode}")
    public ResponseEntity<City> updateCity(@PathVariable Integer zipcode, @RequestBody City cityDetails) 
        throws GlobalException {
        cityDetails.setZipcode(zipcode);
        City updatedCity = cityService.updateCity(cityDetails.getZipcode() , cityDetails);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{zipcode}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer zipcode) throws GlobalException {
        cityService.deleteCity(zipcode);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
}
