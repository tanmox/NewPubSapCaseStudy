package com.sapient.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sap_theater_mov", schema="public")
public class TheaterMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tm_id")
    private int tmID;

    @Column(name = "theater_id", columnDefinition = "int")
    private int theater_id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "theater_id", columnDefinition = "int", nullable = false, insertable = false, updatable = false)
   private Theater theater;

    @Column(name = "movie_id", columnDefinition = "int")
    private int movie_id;

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "movie_id", columnDefinition = "int", nullable = false, insertable = false, updatable = false)
   private Movie movie;

    @Column(name = "screen")
    private int screen;

    @Column(name = "show_time")
    private String show_time;

}
