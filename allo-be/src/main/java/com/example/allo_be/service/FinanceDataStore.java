package com.example.allo_be.service;

import com.example.allo_be.dto.FinanceResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FinanceDataStore {

    private Map<String, List<FinanceResponseDto>> internalData =
            new HashMap<>();

    private Map<String, List<FinanceResponseDto>> immutableData =
            Collections.emptyMap();

    public synchronized void initialize(Map<String, List<FinanceResponseDto>> data) {
        Map<String, List<FinanceResponseDto>> defensiveCopy = new HashMap<>();

        data.forEach((key, value) ->
                defensiveCopy.put(key, List.copyOf(value))
        );

        this.immutableData = Collections.unmodifiableMap(defensiveCopy);
        this.internalData = null;
    }

    public List<FinanceResponseDto> getByResourceType(String resourceType) {
        List<FinanceResponseDto> result = immutableData.get(resourceType);
        return result != null ? result : Collections.emptyList();
    }

    public boolean isInitialized() {
        return !immutableData.isEmpty();
    }
}
