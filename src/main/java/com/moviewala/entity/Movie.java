package com.sapient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "sap_mov", schema="public")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "city")
    private String movieCity;

    @Column(name = "movie_start_date")
    private Date movStartDate;

    @Column(name = "movie_end_date")
    private Date movEndDate;

}
