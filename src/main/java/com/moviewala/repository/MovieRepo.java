package com.sapient.repository;

import com.sapient.entity.Movie;
import com.sapient.entity.TheaterMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String movieName);

    Movie findByMovieId(int movieId);

    @Query(nativeQuery = true, value = "Select * from sap_mov a Where a.movie_name=:movieName and a.city=:city")
    public Movie findByMovieAndCityName(@Param("movieName") String movieName, @Param("city") String city);

}
