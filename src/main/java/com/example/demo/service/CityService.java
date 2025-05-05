package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.City;

public interface CityService {

	City addCity(City city) throws GlobalException;

	City updateCity(Integer zipcode, City cityDetails) throws GlobalException;

	void deleteCity(Integer zipcode) throws GlobalException;

	City getCityByZipcode(Integer zipcode) throws GlobalException;

	List<City> getAllCities();

	City getCityByName(String cityname) throws GlobalException;

	List<City> getCitiesByState(String state);

}
