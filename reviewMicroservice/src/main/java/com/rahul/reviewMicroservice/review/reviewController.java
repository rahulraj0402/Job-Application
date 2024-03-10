package com.rahul.reviewMicroservice.review;

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

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId ,
                                            @RequestBody Review review){

        boolean isReviewSaved = reviewService.addReview(companyId , review);
        if (isReviewSaved){
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


}
