package com.movies_reviews_services.service;

import com.movies_reviews_services.domain.Review;
import com.movies_reviews_services.repository.ReviewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
;
@Service
@Slf4j
public class MoviesReviewService {

    private ReviewsRepository reviewsRepository;

    public MoviesReviewService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Flux<Review> getReviews() {

        return  reviewsRepository.findAll();
    }

    public Mono<Review> addMovieInfo(Review review) {
        return reviewsRepository.save(review);
    }
}
