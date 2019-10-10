package com.tickr.marketcollector.stocks;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Stock {
    @Id
    private String id;

    private String actSymbol;
    private String cqsSymbol;
    private String companyName;
    private String etf;
    private String exchange;
    private String nasdaqSymbol;
    private String roundLotSize;
    private String securityName;
    private String testIssue;

    private Double value;
}
