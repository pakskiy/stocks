package com.pakskiy.stocks.job;

import com.pakskiy.stocks.model.PercentReport;
import com.pakskiy.stocks.model.VolumeReport;
import com.pakskiy.stocks.repository.PercentReportRepository;
import com.pakskiy.stocks.repository.VolumeReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final VolumeReportRepository volumeReportRepository;
    private final PercentReportRepository percentReportRepository;
    @Scheduled(fixedRateString = "5000")
    public void report() {
        List<VolumeReport> volumeReportList = new ArrayList<>();
        List<PercentReport> percentReportList = new ArrayList<>();

        try {
            volumeReportList = volumeReportRepository.getVolumeReport();
        }catch (Exception e){
            log.error("report:volume report", e);
        }

        try {
            percentReportList = percentReportRepository.getPercentReport();
        }catch (Exception e){
            log.error("report:percent report", e);
        }

        log.info("\n\n");
        log.info("==================================================");
        log.info("=================REPORT BY VOLUME=================");
        for(VolumeReport v : volumeReportList){
            log.info(v.toString());
        }
        log.info("==================================================");
        log.info("\n\n");
        log.info("==================================================");
        log.info("=================REPORT BY PERCENT=================");
        for(PercentReport p : percentReportList){
            log.info(p.toString());
        }
        log.info("==================================================");
        log.info("\n\n");
    }
}