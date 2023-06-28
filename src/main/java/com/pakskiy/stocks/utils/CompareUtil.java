package com.pakskiy.stocks.utils;

import com.pakskiy.stocks.model.QuoteEntity;

import java.util.HashMap;
import java.util.Objects;

public class CompareUtil {
    public static boolean isSameRecords(QuoteEntity quoteEntity, HashMap<String, QuoteEntity> quoteHashMap) {
        if(quoteHashMap.containsKey(quoteEntity.getCompanyId())){
            QuoteEntity mapQuoteEntity = quoteHashMap.get(quoteEntity.getCompanyId());
            return compareQuotes(mapQuoteEntity, quoteEntity);
        }
        return false;
    }
    //Checker for diff fields between current and previous quotes
    private static boolean compareQuotes(QuoteEntity quoteEntity, QuoteEntity quoteEntityDto){
        boolean res = Objects.equals(quoteEntity.getLatestPrice(), quoteEntityDto.getLatestPrice())
                && Objects.equals(quoteEntity.getVolume(), quoteEntityDto.getVolume())
                && Objects.equals(quoteEntity.getPreviousVolume(), quoteEntityDto.getPreviousVolume());
        return res;
    }
    //Replace previous_latest_price in quotes if has differences
    public static void replacePreviousPrice(QuoteEntity quoteEntity, HashMap<String, QuoteEntity> quoteHashMap){
        if(quoteHashMap.containsKey(quoteEntity.getCompanyId())){
            Double previousLatestPrice = quoteHashMap.get(quoteEntity.getCompanyId()).getLatestPrice();
            quoteEntity.setPreviousLatestPrice(previousLatestPrice==null ? 0 : previousLatestPrice);
        }
    }
}
