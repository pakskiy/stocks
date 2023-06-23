package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.PercentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PercentReportRepository extends JpaRepository<PercentReport, Integer> {
    @Query(nativeQuery = true, value = "select ROW_NUMBER () OVER () id, a.company_name, round(cast(a.percent as numeric),2)||'%' as percent from ( " +
            "select company_name ||' ['||UPPER(symbol)||']' as company_name, ((latest_price - previous_latest_price) / previous_latest_price)*100 percent from quotes " +
            "where latest_price is not null and previous_latest_price is not null " +
            "and latest_price != previous_latest_price order by 2 desc) a limit 5")
    List<PercentReport> getPercentReport();
}
