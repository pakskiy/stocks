package com.pakskiy.stocks.service;

import com.pakskiy.stocks.connection.iexcloud.IexCloudConnector;
import com.pakskiy.stocks.dto.QuoteDto;
import com.pakskiy.stocks.dto.SymbolsDto;
import com.pakskiy.stocks.model.LoggerSystem;
import com.pakskiy.stocks.model.Quote;
import com.pakskiy.stocks.repository.LoggingSystemRepository;
import com.pakskiy.stocks.repository.QuoteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyDownloadService {
    private final IexCloudConnector iexCloudConnector;
    private final QuoteRepository quoteRepository;
    private final ExecutorService executorService;
    private final LoggingSystemRepository loggingSystemRepository;
    private AtomicInteger counter;
    @PostConstruct
    private void init() {
        startDownload();
    }

    public void startDownload() {
        List<SymbolsDto> symbolsDtoList = iexCloudConnector.loadSymbols();
        counter = new AtomicInteger(symbolsDtoList.size());
        try {
            for(int i=0; i< symbolsDtoList.size(); i++){
                getQuote(symbolsDtoList.get(i).getSymbol().toLowerCase());
//                if(i%5==0){Thread.sleep(1200);}
            }
        }catch (Exception e){
            log.error("startDownload", e);
        }
    }
    private CompletableFuture<Void> getQuote(String symbol) {
        return CompletableFuture.runAsync( () -> {
            saveSymbol(symbol);
            checkQueue();
        }, executorService);
    }

//    private CompletableFuture<String> getQuoteSupply(String symbol) {
//        return CompletableFuture.supplyAsync( () -> {
//            saveSymbol(symbol);
//            return "success";
//        }, executorService).thenApply(name -> {
//            checkQueue();
//            log.info(Thread.currentThread().getName());
//            return "Hello " + name;
//        });
//    }

    private void checkQueue() {
        if(counter.get()==1){
            counter.getAndDecrement();
            startDownload();
            log.info("COUNTER VALUE: " + counter.get());
        }
    }

    @Transactional
    public void saveSymbol(@NotNull String symbol){
        QuoteDto quoteDto = null;
        try {
            quoteDto = iexCloudConnector.loadStock(symbol);
        }catch (Exception e){log.error("Error getting quote by symbol: " + symbol);}

        if(quoteDto!= null){
            boolean needSave;
            Quote quote;
            if((quote = checkQuoteBySymbol(symbol)) != null){
                if(needSave = isSame(quote, quoteDto)){
                    StringBuilder sb = new StringBuilder();
                    sb.append(" latest_price from ");
                    sb.append(quote.getLatestPrice());
                    sb.append(" to ");
                    sb.append(quoteDto.getLatestPrice());
                    sb.append(" volume from ");
                    sb.append(quote.getVolume());
                    sb.append(" to ");
                    sb.append(quoteDto.getVolume());
                    sb.append(" latest_price from ");
                    sb.append(quote.getLatestPrice());
                    sb.append(" to ");
                    sb.append(quoteDto.getLatestPrice());
                    sb.append(" volume from ");
                    sb.append(quote.getVolume());
                    sb.append(" to ");
                    sb.append(quoteDto.getVolume());
                    sb.append(" previous_volume from ");
                    sb.append(quote.getPreviousVolume());
                    sb.append(" to ");
                    sb.append(quoteDto.getPreviousVolume());

                    LoggerSystem loggerSystemRecord = new LoggerSystem();
                    loggerSystemRecord.setSymbolId(symbol.toLowerCase());
                    loggerSystemRecord.setData(sb.toString());
                    loggingSystemRepository.save(loggerSystemRecord);

                    quote.setPreviousLatestPrice(quote.getLatestPrice());
                }
            } else {
                quote.setSymbol(symbol);
                quote.setPreviousLatestPrice(null);
                quote.setCompanyName(quoteDto.getCompanyName());
                needSave = true;
            }

            quote.setUpdated_at(ZonedDateTime.now());
            quote.setLatestPrice(quoteDto.getLatestPrice());
            quote.setVolume(quoteDto.getVolume());
            quote.setPreviousVolume(quoteDto.getPreviousVolume());
            if(needSave) {quoteRepository.save(quote);}
        } else {
            log.warn("Oops, no data for symbol: " + symbol);
        }
    }

    private Quote checkQuoteBySymbol(String symbol){
        return quoteRepository.getQuoteBySymbol(symbol.toLowerCase()).orElse(null);
    }

    private boolean isSame(Quote quote, QuoteDto quoteDto){
        return !Objects.equals(quote.getLatestPrice(), quoteDto.getLatestPrice())
                || !Objects.equals(quote.getVolume(), quoteDto.getVolume())
                || !Objects.equals(quote.getPreviousVolume(), quoteDto.getPreviousVolume());
    }
}