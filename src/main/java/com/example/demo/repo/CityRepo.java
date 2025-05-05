package com.example.demo.repo;

import com.example.demo.modal.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Integer> {
    Optional<City> findByCityname(String cityname);
    List<City> findByState(String state);
    boolean existsByCityname(String cityname);
    
    // Corrected JPQL query for city search
    @Query("SELECT c FROM City c WHERE c.cityname LIKE %:city%")
    List<City> searchByCityName(@Param("city") String city);
}
