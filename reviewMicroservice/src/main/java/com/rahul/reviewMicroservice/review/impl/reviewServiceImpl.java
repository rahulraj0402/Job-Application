package com.rahul.reviewMicroservice.review.impl;


import com.rahul.reviewMicroservice.review.Review;
import com.rahul.reviewMicroservice.review.ReviewRepository;
import com.rahul.reviewMicroservice.review.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class reviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        if (companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview( Long reviewId) {
      Review review = reviewRepository.findById(reviewId).orElse(null);
      return review;

    }

    @Override
    public boolean updateReview( Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview( Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null ){
            reviewRepository.deleteById(reviewId);
            return true;

        }
        return false;
    }


}










