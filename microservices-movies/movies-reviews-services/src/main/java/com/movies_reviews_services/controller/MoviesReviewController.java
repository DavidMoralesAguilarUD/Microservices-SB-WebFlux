package com.movies_reviews_services.controller;
import com.movies_reviews_services.domain.Review;
import com.movies_reviews_services.service.MoviesReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Flux<Review> getAllMovieReviews() {
        return reviewService.getReviews();
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Review> addMovieReview(@RequestBody Review review) {
        return reviewService.addReview(review)
                .doOnNext(savedMovieInfo -> movieInfoSink.tryEmitNext(savedMovieInfo));

    }

    @GetMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)

    public Mono<Review> getMovieReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }
    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Review>> updateMovieReview(@RequestBody Review review, @PathVariable String id) {
        var updatedReviewInfo = reviewService.updateReview(review, id);
        return updatedReviewInfo
                .map(movieReview1 -> ResponseEntity.ok()
                        .body(movieReview1))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovieReviewById(@PathVariable String id){
        return reviewService.deleteReviewInfoById(id);

    }
}
