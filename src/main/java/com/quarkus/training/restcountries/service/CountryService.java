package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.dto.CountryInDto;
import com.quarkus.training.restcountries.dto.CountryOutDto;
import com.quarkus.training.restcountries.model.Country;
import com.quarkus.training.restcountries.repository.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CountryService {

    @Inject
    CountryRepository repo;

    public List<CountryOutDto> fetchByCurrency(String currency) {
        return repo.findByCurrencyPanache(currency)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CountryOutDto fetchByCode(String code) {
        return repo.findByCodePanache(code)
                .map(this::toDto)
                .orElse(null);
    }

    private CountryOutDto toDto(Country c) {
        return new CountryOutDto(
                c.commonName,
                c.code,
                c.officialName,
                c.currencies.stream().toList()
        );
    }
    public Country toEntity(CountryInDto c) {
        Country entity = new Country();
        entity.code = c.code;
        entity.commonName = c.getCommonName();
        entity.officialName = c.getOfficialName();
        entity.currencies = c.getCurrencyCodes();
        return entity;
    }
}
