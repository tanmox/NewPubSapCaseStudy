package com.sapient.repository;


import com.sapient.entity.Movie;
import com.sapient.entity.TheaterMovie;
import com.sapient.response.TheaterMovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TheaterMovieRepo extends JpaRepository<TheaterMovie, Integer> {

    TheaterMovie findByTmID(int tmID);

    @Query(nativeQuery = true, value = "Select c.tm_id, c.screen, a.movie_name, a.movie_id, b.name, b.theater_id, b.location, b.zipcode, c.show_time from sap_theater_mov c join sap_mov a on a.movie_id = c.movie_id join sap_theater b on b.theater_id = c.theater_id Where a.movie_name=:movieName and b.city=:city")
    public ArrayList<TheaterMovie> findByCityAndMovie(@Param("movieName") String movieName, @Param("city") String city);

}
