package com.example.allo_be.strategy;

import com.example.allo_be.client.FrankfurterApiClient;
import com.example.allo_be.dto.FinanceResponseDto;
import com.example.allo_be.dto.HistoricalRatesResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HistoricalIdrUsdStrategy implements IDRDataFetcher{

    private final FrankfurterApiClient apiClient;

    public HistoricalIdrUsdStrategy(FrankfurterApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String resourceType() {
        return ResourceTypes.HISTORICAL_IDR_USD;
    }

    @Override
    public List<FinanceResponseDto> fetch() {
        HistoricalRatesResponse response = apiClient.fetchHistoricalIdrUsd();

        return response.getRates().entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // SORT DATE
                .map(entry -> {
                    String date = entry.getKey();
                    Double usdRate = entry.getValue().get("USD");
                    return new FinanceResponseDto(date, usdRate);
                })
                .toList();
    }

}
