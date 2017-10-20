package com.techprimers.stock.dbservice.repository;

import com.techprimers.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author brunorocha
 */
public interface QuoteRepository extends JpaRepository<Quote, Integer>{

    List<Quote> findByUserName(String username);
}
