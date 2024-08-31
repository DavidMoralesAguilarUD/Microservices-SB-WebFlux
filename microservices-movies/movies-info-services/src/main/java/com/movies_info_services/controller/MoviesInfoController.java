package com.movies_info_services.controller;

import com.movies_info_services.domain.MovieInfo;
import com.movies_info_services.service.MoviesInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/v1")
@Slf4j
public class MoviesInfoController {

    private MoviesInfoService moviesInfoService;

    public MoviesInfoController(MoviesInfoService moviesInfoService) {
        this.moviesInfoService = moviesInfoService;
    }
    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)

    public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo) {
        return moviesInfoService.addMovieInfo(movieInfo);
    }
    @GetMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MovieInfo> getMovieInfoById(@PathVariable String id) {
        return moviesInfoService.getMovieInfoById(id);
    }

    @GetMapping("/moviesInfo")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MovieInfo> getallMoviesinfos(){
        return moviesInfoService.getallMoviesinfos();
    }


    @PutMapping("/movieinfo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<MovieInfo>> updateMovieInfo(@RequestBody MovieInfo movieInfo, @PathVariable String id) {
        var updatedMovieInfo = moviesInfoService.updateMovieInfo(movieInfo, id);
        return updatedMovieInfo
                .map(movieInfo1 -> ResponseEntity.ok()
                        .body(movieInfo1))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovieInfoById(@PathVariable String id){
        return moviesInfoService.deleteMovieInfoById(id);

    }
}
