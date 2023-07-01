package com.pakskiy.stocks.job;

import com.pakskiy.stocks.connection.iexcloud.IexCloudConnector;
import com.pakskiy.stocks.dto.QuoteDto;
import com.pakskiy.stocks.dto.SymbolsDto;
import com.pakskiy.stocks.model.CompanyEntity;
import com.pakskiy.stocks.model.QuoteEntity;
import com.pakskiy.stocks.repository.CompanyRepository;
import com.pakskiy.stocks.repository.QuoteRepository;
import com.pakskiy.stocks.utils.CompareUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadJob {
    private final IexCloudConnector iexCloudConnector;
    private final QuoteRepository quoteRepository;
    private final CompanyRepository companyRepository;

    @Scheduled(fixedRateString ="${app.download.timeout}", initialDelay=2000)
    public void startDownload() {
        //getting data from API for companies
        List<SymbolsDto> symbolsDtoList = iexCloudConnector.loadSymbols();
        List<CompanyEntity> companyEntityList = symbolsDtoList.stream().map(SymbolsDto::toEntity).toList();
        try {
            companyRepository.saveAll(companyEntityList);
        }catch (Exception e){
            log.error("startDownload::saveCompanies", e);
        }

        //getting quote data from API for each company
        List<QuoteEntity> quoteEntityFromApiList = companyEntityList.parallelStream()
                .map(companyEntity -> iexCloudConnector.loadStock(companyEntity.getId().toLowerCase()).join())
                .filter(Objects::nonNull)
                .toList()
                //here again stream from list because getting NullPointerException for CallableFuture from loadStock method when 429 http error
                .stream()
                .map(QuoteDto::toEntity)
                .toList();

        List<QuoteEntity> quoteEntityFromDbList = quoteRepository.getQuotes();

        if(!quoteEntityFromDbList.isEmpty()){
            //here HashMap for compare values with quoteEntityFromApiList
            HashMap<String, QuoteEntity> quoteDtoHashMapFromDb = (HashMap<String, QuoteEntity>) quoteEntityFromDbList.stream().collect(Collectors.toMap(QuoteEntity::getCompanyId, Function.identity()));

            //compare API list with DB list
            quoteEntityFromApiList = quoteEntityFromApiList.stream()
                    .filter(x -> !CompareUtil.isSameRecords(x, quoteDtoHashMapFromDb))
                    .peek(x -> CompareUtil.replacePreviousPrice(x, quoteDtoHashMapFromDb))
                    .collect(Collectors.toList());
        }
        quoteRepository.saveAll(quoteEntityFromApiList);
    }
}