package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, String> {
    @Query(nativeQuery = true, value = "select a.id, a.company_id, a.volume, a.previous_volume, a.latest_price, a.previous_latest_price, a.created_at, a.updated_at from (" +
            "select id, company_id, volume, previous_volume, latest_price, previous_latest_price, created_at, updated_at, rank() over (partition by company_id order by created_at desc) as rnk from quotes\n" +
            "order by company_id, rnk) a where a.rnk = 1 order by company_id")
    List<QuoteEntity> getQuotes();
}