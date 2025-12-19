package com.example.allo_be.strategy;

import com.example.allo_be.client.FrankfurterApiClient;
import com.example.allo_be.dto.FinanceResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SupportedCurrenciesStrategyTest {

    @Test
    void shouldReturnSupportedCurrencies() {

        FrankfurterApiClient client = Mockito.mock(FrankfurterApiClient.class);

        Mockito.when(client.fetchSupportedCurrencies())
                .thenReturn(Map.of(
                        "USD", "United States Dollar",
                        "EUR", "Euro"
                ));

        SupportedCurrenciesStrategy strategy =
                new SupportedCurrenciesStrategy(client);

        List<FinanceResponseDto> result = strategy.fetch();

        assertThat(result).hasSize(2);

        assertThat(result)
                .extracting(FinanceResponseDto::getKey)
                .containsExactlyInAnyOrder("USD", "EUR");

        assertThat(result)
                .extracting(FinanceResponseDto::getValue)
                .containsExactlyInAnyOrder("United States Dollar", "Euro");
    }

}
