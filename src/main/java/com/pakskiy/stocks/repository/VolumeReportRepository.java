package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.VolumeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeReportRepository extends JpaRepository<VolumeReportEntity, Integer> {
    @Query(nativeQuery = true, value = "select ROW_NUMBER () OVER () id, a.company_name, a.volume " +
            "FROM  (" +
            "SELECT c.name ||' ['||UPPER(c.id)||']' as company_name, GREATEST(COALESCE(q.volume, 0), COALESCE(q.previous_volume, 0)) volume, q.latest_price, q.created_at, q.updated_at, RANK() OVER (PARTITION BY q.company_id ORDER BY q.created_at DESC) as rnk " +
            "FROM quotes q, companies c " +
            "WHERE q.company_id = c.id " +
            "ORDER BY 2 DESC " +
            ") a WHERE a.rnk = 1 LIMIT 5")
    List<VolumeReportEntity> getVolumeReport();
}
