package com.pakskiy.stocks.job;

import com.pakskiy.stocks.connection.iexcloud.IexCloudConnector;
import com.pakskiy.stocks.dto.SymbolsDto;
import com.pakskiy.stocks.model.CompanyEntity;
import com.pakskiy.stocks.repository.CompanyRepository;
import com.pakskiy.stocks.repository.QuoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

class LoadJobTest {
    @Mock
    QuoteRepository quoteRepository;
    @Mock
    CompanyRepository companyRepository;
    @BeforeEach
    void beforeAll() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void startDownload() {
//        IexCloudConnector iexCloudConnector = new IexCloudConnector();
//        List<SymbolsDto> symbolsDtoList = iexCloudConnector.loadSymbols();
//        List<CompanyEntity> companyEntityList = symbolsDtoList.stream().map(SymbolsDto::toEntity).toList();
//        System.out.println(symbolsDtoList.size());
    }
}