package com.example.demo;

import com.example.demo.model.AdminReviewAction;
import com.example.demo.model.AppUser;
import com.example.demo.model.DiningReview;
import com.example.demo.model.Restaurant;
import com.example.demo.repositories.DiningReviewRepository;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DiningReviewController {

    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;

    private final UserRepository userRepository;

    public DiningReviewController(RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/restaurants/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantRepository.save(restaurant);
        return newRestaurant;
    }

    @GetMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Restaurant> getRestaurantDetails(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id);

    }

    @GetMapping("/restaurants/search/{zipcode}")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAllRestaurantsByZipcode(@PathVariable String zipcode) {
        return restaurantRepository.getByZipcodeOrderByScoreDesc(zipcode);
    }

    @PostMapping("restaurants/user/new-review")
    @ResponseStatus(HttpStatus.CREATED)
    public DiningReview createDiningReview(@RequestBody DiningReview diningReview) {

        DiningReview review = diningReviewRepository.save(diningReview);
        return review;
    }

    @GetMapping("restaurants/admin/reviews/by-status")
    @ResponseStatus(HttpStatus.OK)
    public List<DiningReview> getReviewByStatus(@RequestParam AdminReviewAction.ReviewStatus status) {
        return diningReviewRepository.getByStatus(status);
    }

    @PutMapping("restaurants/admin/reviews/set-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiningReview acceptOrRejectReview(@PathVariable Long id) {
        Optional<DiningReview> diningReviewOptional = diningReviewRepository.findById(id);
        if (!diningReviewOptional.isPresent()) {
            return null;
        }
        DiningReview review = diningReviewOptional.get();
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());
        if (!restaurantOptional.isPresent()) {
            return null;
        }
        if (review.getStatus().equals(AdminReviewAction.ReviewStatus.PENDING)) {
            Iterable<AppUser> userList = userRepository.findAll();
            for(AppUser u: userList){
                if(review.getName().equals(u.getDisplayName())){
                    if(review.getRestaurantId() != 0){
                        review.setStatus(AdminReviewAction.ReviewStatus.ACCEPTED);
                        Restaurant restaurant = restaurantOptional.get();
                        int eggRating = restaurant.getEggRating() + review.getEggScore();
                        int peanutRating = restaurant.getPeanutRating() + review.getPeanutScore();
                        int dairyRating = restaurant.getDairyRating() + review.getDairyScore();
                        restaurant.setEggRating(eggRating);
                        restaurant.setPeanutRating(peanutRating);
                        restaurant.setDairyRating(dairyRating);
                        restaurant.setScore(eggRating + peanutRating + dairyRating);
                        restaurantRepository.save(restaurant);
                        diningReviewRepository.save(review);
                    }
                } else{
                    review.setStatus(AdminReviewAction.ReviewStatus.REJECTED);
                    diningReviewRepository.save(review);
                }
            }
        }
        return review;
    }

    @GetMapping("restaurants/admin/reviews/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DiningReview> getAcceptedReviews(@PathVariable Long id, @RequestParam AdminReviewAction.ReviewStatus status) {
        if (status.equals(AdminReviewAction.ReviewStatus.ACCEPTED)) {
            return diningReviewRepository.getByStatusAndRestaurantId(status, id);
        }
        return new ArrayList<>();
    }

    @PostMapping("restaurants/user/new-user")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createNewUser(@RequestBody AppUser user) {
        Iterable<AppUser> allUsers = userRepository.findAll();
        for (AppUser u : allUsers) {
            if (user.getDisplayName().equals(u.getDisplayName())) {
                //throw an error
            }
        }
        AppUser appUser = userRepository.save(user);
        return appUser;
    }

    @GetMapping("restaurants/user/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AppUser getUser(@PathVariable String name) {
        return userRepository.getByDisplayName(name);
    }

}
