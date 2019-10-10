package com.tickr.marketcollector.stocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

@RunWith(SpringRunner.class)
public class StockRepositoryInitializerTest {

    private StockRepositoryInitializer stockRepositoryInitializer;
    @MockBean
    private MongoTemplate mongoTemplate;
    @MockBean
    private MongoMappingContext mongoMappingContext;
    @MockBean
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.stockRepositoryInitializer = new StockRepositoryInitializer(mongoTemplate, mongoMappingContext, objectMapper);
    }

    @Test
    public void generateRandomStockValue_shouldReturnValueBetween0And1000() {
        Assertions.assertThat(stockRepositoryInitializer.generateRandomStockValue(0.0, 1000.0))
                .isBetween(0.0, 1000.0);
    }
}
