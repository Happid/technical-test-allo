package com.example.allo_be.client;

import com.example.allo_be.dto.CurrenciesResponse;
import com.example.allo_be.dto.HistoricalRatesResponse;
import com.example.allo_be.dto.LatestRatesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FrankfurterApiClient {
    private final WebClient webClient;

    public FrankfurterApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public LatestRatesResponse fetchLatestIdrRates() {
        return webClient.get()
                .uri("/latest?base=IDR")
                .retrieve()
                .bodyToMono(LatestRatesResponse.class)
                .block();
    }

    public HistoricalRatesResponse fetchHistoricalIdrUsd() {
        return webClient.get()
                .uri("/2024-01-01..2024-01-05?from=IDR&to=USD")
                .retrieve()
                .bodyToMono(HistoricalRatesResponse.class)
                .block();
    }

    public CurrenciesResponse fetchSupportedCurrencies() {
        return webClient.get()
                .uri("/currencies")
                .retrieve()
                .bodyToMono(CurrenciesResponse.class)
                .block();
    }
}
