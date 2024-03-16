package com.example.demo.repositories;

import com.example.demo.model.AdminReviewAction;
import com.example.demo.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {

/**
 * As a registered user, I want to submit a dining review.
 * As an admin, I want to get the list of all dining reviews that are pending approval.
 * As an admin, I want to approve or reject a given dining review.
 * As part of the backend process that updates a restaurant’s set of scores, I want to fetch the set of all approved dining reviews belonging to this restaurant.
 */
    List<DiningReview> getByStatus(AdminReviewAction.ReviewStatus status);
    List<DiningReview> getByStatusAndRestaurantId(AdminReviewAction.ReviewStatus status, Long id);


}
