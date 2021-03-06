package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuoteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author brunorocha
 */
@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    private QuoteRepository quotesRepository;

    public DbServiceResource(QuoteRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username) {
        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes, HttpServletResponse response) {
        setHeaderResponse(response);
        quotes.getQuotes()
                .stream()
                .map(quote -> Quote.of(quotes.getUserName(), quote))
                .forEach(quotesRepository::save);
        return getQuotesByUserName(quotes.getUserName());
    }


    private void setHeaderResponse(HttpServletResponse response) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(new Random(new Random().nextLong())).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") String username) {
        List<Quote> byUserName = quotesRepository.findByUserName(username);
        quotesRepository.delete(byUserName);

        return getQuotesByUserName(username);
    }
}
