package com.sapient.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "sap_theater", schema="public")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private int id;

    @Column(name = "created_at")
    private Date created;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "location")
    private String location;

    @Column(name = "zipcode")
    private String zipCode;

}
