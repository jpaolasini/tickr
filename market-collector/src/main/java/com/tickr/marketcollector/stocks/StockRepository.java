package com.tickr.marketcollector.stocks;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StockRepository extends ReactiveMongoRepository<Stock, String> {

    Mono<Stock> findByNasdaqSymbol();
}
