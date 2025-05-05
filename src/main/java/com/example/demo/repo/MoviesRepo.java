package com.example.demo.repo;

import com.example.demo.modal.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MoviesRepo extends JpaRepository<Movies, Integer> {
    
    // Fixed query with proper parameter usage
    @Query(value = "SELECT m.* FROM movies m " +
            "JOIN movieshow ms ON m.movieid = ms.movieid " +
            "WHERE ms.date > CURRENT_DATE", 
            nativeQuery = true)
    List<Movies> findMoviesWithUpcomingShows();

    List<Movies> findByTitleContainingIgnoreCase(String title);
    List<Movies> findByLanguage(String language);
    boolean existsByTitle(String title);
}
