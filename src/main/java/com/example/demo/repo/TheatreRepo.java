package com.example.demo.repo;

import com.example.demo.modal.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TheatreRepo extends JpaRepository<Theatre, Integer> {

    // Correct JPQL for theatre-city relationship
    @Query("SELECT t FROM Theatre t WHERE t.city.zipcode = :zipcode")
    List<Theatre> findByCityZipcode(@Param("zipcode") Integer zipcode);

    // Removed native insert query (use save() instead)
    Theatre findByTheatreName(String theatrename);
    List<Theatre> findByCity_Cityname(String cityName);
    List<Theatre> findByTheatreNameContainingIgnoreCase(String query);
}
