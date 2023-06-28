package com.pakskiy.stocks.connection.iexcloud;

import com.pakskiy.stocks.connection.Connector;
import com.pakskiy.stocks.dto.QuoteDto;
import com.pakskiy.stocks.dto.SymbolsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class IexCloudConnector implements Connector<SymbolsDto, QuoteDto> {
    @Value("${platform.iex.token}")
    private String TOKEN;

    @Value("${platform.iex.url.symbol}")
    private String URL_SYMBOL;

    @Value("${platform.iex.url.quote}")
    private String URL_QUOTE;

    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<SymbolsDto> loadSymbols() {
        try {
            String resourceUrl = URL_SYMBOL + "?token=" + TOKEN;
            ResponseEntity<List<SymbolsDto>> responseEntity =
                    restTemplate.exchange(
                            resourceUrl,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<>() {
                            }
                    );
            return responseEntity.getBody();
        }catch (Exception e){
            log.error("loadSymbols", e);
            return List.of();
        }
    }

    @Override
    public CompletableFuture<QuoteDto> loadStock(String name) {
        try {
            String resourceUrl = URL_QUOTE + "/" + name + "/quote?token=" + TOKEN;
            return CompletableFuture.completedFuture(restTemplate.getForObject(resourceUrl, QuoteDto.class));
        }catch (HttpClientErrorException er){
            log.error("loadStock: [" + name + "] [" + er.getStatusCode() + "]");
        }catch (Exception e){
            log.error("loadStock: " + e);
        }
        return CompletableFuture.completedFuture(null);
    }
}
