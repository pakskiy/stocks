package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.VolumeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeReportRepository extends JpaRepository<VolumeReport, Integer> {
    @Query(nativeQuery = true, value = "select ROW_NUMBER () OVER () id, a.company_name, a.volume from " +
            "(select company_name ||' ['||UPPER(symbol)||']' as company_name, GREATEST(COALESCE(volume, 0), COALESCE(previous_volume, 0))  volume " +
            "from quotes " +
            "where previous_volume is not null or volume is not null order by 2 desc) a limit 5")
    List<VolumeReport> getVolumeReport();
}
