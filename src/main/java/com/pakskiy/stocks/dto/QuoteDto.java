package com.pakskiy.stocks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pakskiy.stocks.model.QuoteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteDto {
    private final String symbol;
    private final Long avgTotalVolume;
    private final String calculationPrice;
    private final Double change;
    private final Double changePercent;
    private final String companyName;
    private final Double close;
    private final String closeSource;
    private final Double closeTime;
    private final String currency;
    private final Double delayedPrice;
    private final Double delayedPriceTime;
    private final Double extendedPrice;
    private final Double extendedChange;
    private final Double extendedChangePercent;
    private final Double extendedPriceTime;
    private final Double high;
    private final String highSource;
    private final Long highTime;
    private final Double iexAskPrice;
    private final Double iexAskSize;
    private final Double iexBidPrice;
    private final Double iexBidSize;
    private final Double iexClose;
    private final Double iexCloseTime;
    private final Double iexLastUpdated;
    private final Double iexOpen;
    private final Long iexOpenTime;
    private final Double iexRealtimePrice;
    private final Double iexRealtimeSize;
    private final Double iexMarketPercent;
    private final Double iexVolume;
    private final boolean isUSMarketOpen;
    private final Long lastTradeTime;
    private final Double latestPrice;
    private final String latestSource;
    private final String latestTime;
    private final Long latestUpdate;
    private final Long latestVolume;
    private final Double low;
    private final Long lowTime;
    private final String lowSource;
    private final Double marketCap;
    private final Double oddLotDelayedPrice;
    private final Double oddLotDelayedPriceTime;
    private final Long open;
    private final String openSource;
    private final Long openTime;
    private final Double peRatio;
    private final Double previousClose;
    private final Long previousVolume;
    private final String primaryExchange;
    private final Double week52High;
    private final Double week52Low;
    private final Long volume;
    private final Double ytdChange;

    public QuoteEntity toEntity(){
        return QuoteEntity.builder().companyId(symbol)
                .volume(volume)
                .previousVolume(previousVolume)
                .latestPrice(latestPrice)
                .previousLatestPrice(null)
                .created_at(ZonedDateTime.now())
                .updated_at(ZonedDateTime.now()).build();
    }
}