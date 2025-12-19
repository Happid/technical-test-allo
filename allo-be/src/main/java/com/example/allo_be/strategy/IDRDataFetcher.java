package com.example.allo_be.strategy;

import java.util.List;
import com.example.allo_be.dto.FinanceResponseDto;

public interface IDRDataFetcher {
    String resourceType();
    List<FinanceResponseDto> fetch();
}
