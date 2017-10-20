package com.techprimers.stock.dbservice.model;

import java.util.List;

/**
 * @author brunorocha
 */
public class Quotes {

    private String userName;
    private List<String> quotes;

    private Quotes(String userName, List<String> quotes) {
        this.userName = userName;
        this.quotes = quotes;
    }

    public Quotes() {
    }

    public static Quotes of(String userName, List<String> quotes) {
        return new Quotes(userName, quotes);
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
