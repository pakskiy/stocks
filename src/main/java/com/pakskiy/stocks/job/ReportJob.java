package com.pakskiy.stocks.job;

import com.pakskiy.stocks.model.PercentReportEntity;
import com.pakskiy.stocks.model.VolumeReportEntity;
import com.pakskiy.stocks.repository.PercentReportRepository;
import com.pakskiy.stocks.repository.VolumeReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportJob {
    private final VolumeReportRepository volumeReportRepository;
    private final PercentReportRepository percentReportRepository;

    @Scheduled(fixedRateString ="${app.report.timeout}", initialDelay=2000)
    public void report() {
        List<VolumeReportEntity> volumeReportEntityList = new ArrayList<>();
        List<PercentReportEntity> percentReportEntityList = new ArrayList<>();

        try {
            volumeReportEntityList = volumeReportRepository.getVolumeReport();
        }catch (Exception e){
            log.error("Report:volume report", e);
        }

        try {
            percentReportEntityList = percentReportRepository.getPercentReport();
        }catch (Exception e){
            log.error("Report:percent report", e);
        }

        log.info("\n\n");
        log.info("==================================================");
        log.info("=================REPORT BY VOLUME=================");
        for(VolumeReportEntity v : volumeReportEntityList){
            log.info(v.toString());
        }
        log.info("==================================================");
        log.info("\n\n");
        log.info("==================================================");
        log.info("=================REPORT BY PERCENT=================");
        for(PercentReportEntity p : percentReportEntityList){
            log.info(p.toString());
        }
        log.info("==================================================");
        log.info("\n\n");
    }
}