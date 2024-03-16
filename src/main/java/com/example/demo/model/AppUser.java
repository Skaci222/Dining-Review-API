package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Entity
@RequiredArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String displayName;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String state;
    @Getter @Setter
    private String zipcode;
    @Getter @Setter
    private boolean isInterestedInPeanutAllergies;
    @Getter @Setter
    private boolean isInterestedInEggAllergies;
    @Getter @Setter
    private boolean isInterestedInDairyAllergies;


}
