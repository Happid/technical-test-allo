package com.example.allo_be.strategy;

import com.example.allo_be.client.FrankfurterApiClient;
import com.example.allo_be.dto.FinanceResponseDto;
import com.example.allo_be.dto.LatestRatesResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LatestIdrRatesStrategy implements IDRDataFetcher{

    private static final String GITHUB_USERNAME = "yourgithubusername"; // GANTI

    private final FrankfurterApiClient apiClient;

    public LatestIdrRatesStrategy(FrankfurterApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String resourceType() {
        return ResourceTypes.LATEST_IDR_RATES;
    }

    @Override
    public List<FinanceResponseDto> fetch() {
        LatestRatesResponse response = apiClient.fetchLatestIdrRates();

        Map<String, Double> rates = response.getRates();
        Double usdRate = rates.get("USD");

        double spreadFactor = calculateSpreadFactor(GITHUB_USERNAME);
        double usdBuySpreadIdr = (1 / usdRate) * (1 + spreadFactor);

        List<FinanceResponseDto> result = new ArrayList<>();

        rates.forEach((currency, rate) ->
                result.add(new FinanceResponseDto(currency, rate))
        );

        result.add(new FinanceResponseDto("USD_BuySpread_IDR", usdBuySpreadIdr));

        return result;
    }

    private double calculateSpreadFactor(String username) {
        int sum = username.toLowerCase()
                .chars()
                .sum();

        return (sum % 1000) / 100000.0;
    }

}
