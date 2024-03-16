package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class AdminReviewAction {

    public enum ReviewStatus {
        PENDING, ACCEPTED, REJECTED
    }
}
