package com.techprimers.stock.stockservice.resource;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brunorocha
 */
@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    DbServiceClient dbServiceClient;

    private static final Log log = LogFactory.getLog(StockResource.class);

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String username) throws Exception {

        Application application = eurekaClient.getApplication("db-service");
        InstanceInfo serviceInstance = application.getInstances().get(0);

        final String serviceId = serviceInstance.getInstanceId();

        log.fatal("--------> CALL FROM SERVER: " + serviceId);
        List<String> userQuotes = dbServiceClient.usersQuotes(username);
        return userQuotes.stream()
                .map(quote -> {
                    Stock stock = getStockPrice(quote);
                    return new Quote(quote, stock.getQuote().getPrice());
                }).collect(Collectors.toList());

    }

//    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<?> addQuote(@RequestBody final QuotesUser quotesUser) {
//        checkNotNull(quotesUser, "The user cannot be null");
//
//        final ServiceInstance serviceInstance = loadBalancer.choose("db-service");
//
//        final String serviceId = serviceInstance.getServiceId();
//        final int port = serviceInstance.getPort();
//
//        final String baseUrl = "http://" + serviceId + ":" + port + "/rest/db/add";
//        log.info(baseUrl);
//
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                baseUrl,
//                HttpMethod.POST,
//                new HttpEntity<>(quotesUser), String.class
//        );
//
//        log.info("Adding new user quote for: " + quotesUser.getUserName());
//
//        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
//    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    public List<Quote> fallback(@PathVariable("username") final String username) {
        return Arrays.asList(new Quote(username, new BigDecimal(0)));
    }

    private Stock getStockPrice(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }
}

class Quote {
    private String quote;
    private BigDecimal price;

    public Quote(String quote, BigDecimal price) {
        this.quote = quote;
        this.price = price;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

class QuotesUser {

    private String userName;
    private List<String> quotes;

    public QuotesUser(String userName, List<String> quotes) {
        this.userName = userName;
        this.quotes = quotes;
    }

    public QuotesUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}

@FeignClient("db-service")
interface DbServiceClient {

    @RequestMapping("/rest/db/{name}")
    List<String> usersQuotes(@RequestParam("name") String name);
}