package com.movies_info_services.repository;

import com.movies_info_services.domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MovieInfoRepository  extends ReactiveMongoRepository<MovieInfo, String> {

    Mono<MovieInfo> findById(String name);
}
