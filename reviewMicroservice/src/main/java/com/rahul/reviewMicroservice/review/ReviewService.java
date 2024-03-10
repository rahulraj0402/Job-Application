package com.rahul.reviewMicroservice.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReview(Long companyId);
    boolean addReview(Long companyId , Review review );
    Review getReview( Long reviewId );
    boolean updateReview(Long reviewId , Review review );
    boolean deleteReview(Long reviewId);


}
