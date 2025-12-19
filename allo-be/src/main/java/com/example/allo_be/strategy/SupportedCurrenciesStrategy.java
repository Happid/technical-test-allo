package com.example.allo_be.strategy;

import com.example.allo_be.client.FrankfurterApiClient;
import com.example.allo_be.dto.CurrenciesResponse;
import com.example.allo_be.dto.FinanceResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SupportedCurrenciesStrategy implements IDRDataFetcher{
    private final FrankfurterApiClient apiClient;

    public SupportedCurrenciesStrategy(FrankfurterApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String resourceType() {
        return ResourceTypes.SUPPORTED_CURRENCIES;
    }

    @Override
    public List<FinanceResponseDto> fetch() {
        CurrenciesResponse response = apiClient.fetchSupportedCurrencies();

        List<FinanceResponseDto> result = new ArrayList<>();

        for (Map.Entry<String, String> entry : response.getCurrencies().entrySet()) {
            result.add(new FinanceResponseDto(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}
