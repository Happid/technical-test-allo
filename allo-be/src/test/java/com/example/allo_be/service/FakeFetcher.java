package com.example.allo_be.service;

import com.example.allo_be.dto.FinanceResponseDto;
import com.example.allo_be.strategy.IDRDataFetcher;

import java.util.List;

public class FakeFetcher implements IDRDataFetcher {
    private final String resourceType;

    public FakeFetcher(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String resourceType() {
        return resourceType;
    }

    @Override
    public List<FinanceResponseDto> fetch() {
        return List.of(
                new FinanceResponseDto("TEST_KEY", 123.45)
        );
    }
}
