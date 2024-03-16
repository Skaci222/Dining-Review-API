package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private long restaurantId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int peanutScore;
    @Getter @Setter
    private int eggScore;
    @Getter @Setter
    private int dairyScore;
    @Getter @Setter
    private String commentary;
    @Getter @Setter
    private AdminReviewAction.ReviewStatus status = AdminReviewAction.ReviewStatus.PENDING;




}
