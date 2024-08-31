package com.movies_reviews_services.repository;

import com.movies_reviews_services.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewsRepository extends ReactiveMongoRepository<Review, String> {
}
