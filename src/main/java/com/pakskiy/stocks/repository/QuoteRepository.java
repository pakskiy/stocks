package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
    @Query(nativeQuery = true, value = "select * from quotes where symbol = :symbol")
    Optional<Quote> getQuoteBySymbol(String symbol);
}
