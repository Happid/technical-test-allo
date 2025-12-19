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

        List<FinanceResponseDto> result = new ArrayList<>();

        for (Map.Entry<String, Map<String, Double>> entry : response.getRates().entrySet()) {
            result.add(new FinanceResponseDto(entry.getKey(), entry.getValue()));
        }

        return result;
    }

}
