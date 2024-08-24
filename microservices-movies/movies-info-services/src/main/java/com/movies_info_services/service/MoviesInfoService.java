package com.movies_info_services.service;

import com.movies_info_services.domain.MovieInfo;
import com.movies_info_services.repository.MovieInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@Slf4j
public class MoviesInfoService {

    private MovieInfoRepository movieInfoRepository;

    public MoviesInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo (MovieInfo movieInfo) {
        log.info("addMovieInfo : {} " , movieInfo );
        return movieInfoRepository.save(movieInfo);
    }

    public Mono<MovieInfo> getMovieInfoById(String id) {
        return movieInfoRepository.findById(id);
    }



}
