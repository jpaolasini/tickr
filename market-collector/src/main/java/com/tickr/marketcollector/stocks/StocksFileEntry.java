package com.tickr.marketcollector.stocks;

import lombok.Data;

@Data
public class StocksFileEntry {
    private String actSymbol;
    private String cqsSymbol;
    private String companyName;
    private String etf;
    private String exchange;
    private String nasdaqSymbol;
    private String roundLotSize;
    private String securityName;
    private String testIssue;
}
