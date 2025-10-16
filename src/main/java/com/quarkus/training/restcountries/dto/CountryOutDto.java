package com.quarkus.training.restcountries.dto;

import java.util.Set;

public record CountryOutDto(
        String commonName,
        String code,
        String officialName,
        Set<String> currencies
) {}

