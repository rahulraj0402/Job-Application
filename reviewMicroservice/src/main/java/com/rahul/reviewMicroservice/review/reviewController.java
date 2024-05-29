package com.rahul.reviewMicroservice.review;

import com.rahul.reviewMicroservice.review.messaging.ReviewProducer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/reviews")
public class reviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewProducer reviewProducer;



    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId ,
                                            @RequestBody Review review){

        boolean isReviewSaved = reviewService.addReview(companyId , review);
        if (isReviewSaved){
            reviewProducer.sendMessage(review);
            return new ResponseEntity<>("Review is added" , HttpStatus.CREATED);
        }
        else {
           return new ResponseEntity<>("Review is not saved" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId ){
        return new ResponseEntity<>(reviewService.getReview( reviewId) , HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId ,
                                               @RequestBody Review review){
       boolean isUpdated = reviewService.updateReview( reviewId , review);

       if (isUpdated){
           return new ResponseEntity<>("Updated successfully !! " , HttpStatus.OK);
       }
       return new ResponseEntity<>("Not Updated " , HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){

        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted){
            return new ResponseEntity<>("deleted  successfully !! " , HttpStatus.OK);
        }
        return new ResponseEntity<>("Not deleted !!  " , HttpStatus.NOT_FOUND);

    }

    // every time a new review is added | a api call will be done from company microservice |
    // and this below api will calculate the average ratings and return to company microservice
    @GetMapping("/average-ratings")
    public Double getAverageRating(@RequestParam Long companyId){
        List<Review> all_review_of_a_particular_companyId=reviewService.getAllReview(companyId);
        return all_review_of_a_particular_companyId.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }


}
