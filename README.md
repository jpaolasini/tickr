# tickr 
Demo Stock Market Application using RSocket and Reactive MongoDB

## Status
[![Actions Status](https://xxx.execute-api.us-west-2.amazonaws.com/production/badge/jpaolasini/tickr)](https://xxx.execute-api.us-west-2.amazonaws.com/production/badge/jpaolasini/tickr)


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
