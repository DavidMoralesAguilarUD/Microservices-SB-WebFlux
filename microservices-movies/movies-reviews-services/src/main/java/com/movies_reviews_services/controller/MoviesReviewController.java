package com.movies_reviews_services.controller;
import com.movies_reviews_services.domain.Review;
import com.movies_reviews_services.service.MoviesReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/v1")
@Slf4j
public class MoviesReviewController {

    private MoviesReviewService reviewService;

    Sinks.Many<Review> movieInfoSink = Sinks.many().replay().latest();


    public MoviesReviewController(MoviesReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public Flux<Review> getAllReviews() {
        return reviewService.getReviews();
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Review> addMovieInfo(@RequestBody Review review) {
        return reviewService.addMovieInfo(review)
                .doOnNext(savedMovieInfo -> movieInfoSink.tryEmitNext(savedMovieInfo));

    }


}
