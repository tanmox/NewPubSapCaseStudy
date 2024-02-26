package com.sapient.repository;

import com.sapient.entity.Movie;
import com.sapient.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Integer> {
    Theater findByName(String theaterName);

    Theater findById(int Id);

    @Query(nativeQuery = true, value = "Select * from sap_theater a Where a.name=:name and a.city=:city and a.location=:location and a.zipcode=:zipcode")
    public Theater findByMovieAndCityName(@Param("name") String movieName, @Param("city") String city, @Param("location") String location, @Param("zipcode") String zipcode);
}
