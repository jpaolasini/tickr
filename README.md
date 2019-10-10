# tickr 
Demo Stock Market Application using RSocket and Reactive MongoDB, inspiration from bclozel's https://github.com/bclozel/spring-flights

## Running the Application

From the root of the applciation we will build the **Market Collector** and the **Stock Ticker** microservices. 
```bash
./mvnw clean package
```

### Run Market Collector

```bash
java -jar market-collector/target/market-collector-0.0.1-SNAPSHOT.jar
```

### Run Stock Ticker Web Application

```bash
java -jar stock-ticker/target/stock-ticker-0.0.1-SNAPSHOT.jar
```

## Market Collector

On startup the Market Collector populated the DB from the `stocks.json` resource.
