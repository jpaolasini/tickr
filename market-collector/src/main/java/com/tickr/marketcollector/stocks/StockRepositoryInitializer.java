package com.tickr.marketcollector.stocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@Slf4j
public class StockRepositoryInitializer {

    private final ObjectMapper objectMapper;
    private final MongoMappingContext mongoMappingContext;
    private final MongoTemplate mongoTemplate;

    public StockRepositoryInitializer(MongoTemplate mongoTemplate, MongoMappingContext mongoMappingContext, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.mongoMappingContext = mongoMappingContext;
        this.objectMapper = objectMapper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeStockRepositoryDatabase() throws IOException {
        IndexOperations indexOperations = mongoTemplate.indexOps(Stock.class);
        IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
        resolver.resolveIndexFor(Stock.class).forEach(indexOperations::ensureIndex);
        if (this.mongoTemplate.count(query(Criteria.where("id").exists(true)), Stock.class) == 0) {
            ClassPathResource stocksResource = new ClassPathResource("stocks.json");
            StocksFileEntry[] stockEntries = this.objectMapper
                    .readValue(stocksResource.getInputStream(), StocksFileEntry[].class);

            List<Stock> stocks = Arrays.stream(stockEntries)
                    .map(stock -> Stock.builder()
                            .actSymbol(stock.getActSymbol())
                            .companyName(stock.getCompanyName())
                            .etf(stock.getEtf())
                            .roundLotSize(stock.getRoundLotSize())
                            .securityName(stock.getSecurityName())
                            .testIssue(stock.getTestIssue())
                            .nasdaqSymbol(stock.getNasdaqSymbol())
                            .value(generateRandomStockValue(0.0,1000.0))
                            .build())
                    .collect(Collectors.toList());

            Collection<Stock> insertedStocks = this.mongoTemplate.insert(stocks, Stock.class);
            log.info("Adding {} stocks to the database", insertedStocks.size());
        }

    }

    public Double generateRandomStockValue(Double lower, Double upper) {
        return (Math.random() * (upper - lower)) + lower;
    }
}
