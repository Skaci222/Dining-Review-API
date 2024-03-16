package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@RequiredArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String state;
    @Getter @Setter
    private String zipcode;
    @Getter @Setter
    private int eggRating;
    @Getter @Setter
    private int peanutRating;
    @Getter @Setter
    private int dairyRating;
    @Getter @Setter
    private int score;
}
