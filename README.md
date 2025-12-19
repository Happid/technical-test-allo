# Technical Test Allo - Happid Ridwan Ilmi
Technical Fullstack - React and Java Spring Boot

# 1. Backend
This project is a Spring Boot REST API that aggregates multiple financial resources from the public **Frankfurter Exchange Rate API**, with a primary focus on **Indonesian Rupiah (IDR)** data.

## 1.A. Features

- Single polymorphic REST endpoint
- Strategy Pattern for multi-resource handling
- Startup-only data ingestion (no repeated external calls)
- Immutable, thread-safe in-memory storage
- Personalized spread calculation based on GitHub username
- Unit tests and integration tests (all green)

## 1.B. External API
Base URL:  https://api.frankfurter.app
Resources consumed:
- `/latest?base=IDR`
- `/2024-01-01..2024-01-05?from=IDR&to=USD`
- `/currencies`

## 1.C. Try the API
- `http://localhost:8080/api/finance/data/latest_idr_rates`
- `http://localhost:8080/api/finance/data/historical_idr_usd`
- `http://localhost:8080/api/finance/data/supported_currencies`
- `http://localhost:8080/api/finance/data/unknown`
