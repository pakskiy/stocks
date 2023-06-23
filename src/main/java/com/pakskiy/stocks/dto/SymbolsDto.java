package com.pakskiy.stocks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolsDto {
    private final String symbol;
    private final String exchange;
    private final String name;
    private final String date;
    private final boolean isEnabled;
    private final String type;
    private final String region;
    private final String currency;
    private final String iexId;
    private final String figi;
    private final String cik;

}
