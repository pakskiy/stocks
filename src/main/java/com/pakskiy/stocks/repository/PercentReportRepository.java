package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.PercentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PercentReportRepository extends JpaRepository<PercentReportEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT ROW_NUMBER () OVER () id, a.company_name, round(cast(((a.latest_price - a.previous_latest_price) / a.previous_latest_price)*100 as numeric),2) as percent FROM ( " +
            "SELECT c.name||' ['||c.id||']' as company_name, q.latest_price, q.previous_latest_price, RANK() OVER (PARTITION BY q.company_id ORDER BY q.created_at DESC) as rnk " +
            "FROM quotes q, companies c WHERE c.id = q.company_id " +
            ") a WHERE a.rnk = 1 AND a.latest_price is not null AND a.previous_latest_price is not null " +
            "AND a.latest_price != a.previous_latest_price " +
            "ORDER BY 2 DESC LIMIT 5")
    List<PercentReportEntity> getPercentReport();
}
