package com.quarkus.training.restcountries.dto;

import java.util.List;

public record CountryOutDto(
        String commonName,
        String code,
        String officialName,
        List<String> currencies
) {}

