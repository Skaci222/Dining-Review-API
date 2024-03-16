package com.example.demo.repositories;

import com.example.demo.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    /**As an application experience, I want to submit a new restaurant entry.
     * Should a restaurant with the same name and with the same zip code already exist, I will see a failure.*/


    /**As an application experience, I want to fetch the details of a restaurant, given its unique id.*/


    /**As an application experience, I want to fetch restaurants that match a given zip code and that also have at least
     *   one user-submitted score for a given allergy. I want to see them sorted in descending order.
     */
    List<Restaurant> getByZipcodeOrderByScoreDesc(String zipcode);


}
