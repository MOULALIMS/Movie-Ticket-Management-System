package com.example.demo.repo;

import com.example.demo.modal.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface MovieShowRepo extends JpaRepository<MovieShow, Integer> {
    
    // Fixed date handling
    @Query(value = "SELECT DISTINCT(date) FROM movieshow WHERE date > CURRENT_DATE AND movieid = :movieid ORDER BY date", 
           nativeQuery = true)
    List<Date> findUpcomingShowDates(@Param("movieid") Integer movieid);

    // Corrected parameter types
    @Query("SELECT ms FROM MovieShow ms WHERE ms.date = :date AND ms.movie.movieId = :movieid")
    List<MovieShow> findByDateAndMovie(@Param("date") Date date, 
                                     @Param("movieid") Integer movieid);

    List<MovieShow> findByMovie_MovieId(Integer movieId);
    List<MovieShow> findByDateAndScreen_ScreenId(Date date, Integer screenId);
}
