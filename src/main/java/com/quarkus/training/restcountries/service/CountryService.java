package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.dto.CountryOutDto;
import com.quarkus.training.restcountries.mapper.CountryMapper;
import com.quarkus.training.restcountries.repository.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CountryService {

    private final CountryRepository repo;
    private final CountryMapper mapper;

    public CountryService(CountryRepository repo, CountryMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<CountryOutDto> fetchByCurrency(String currency) {
        return repo.findByCurrencyPanache(currency)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CountryOutDto fetchByCode(String code) {
        return repo.findByCodePanache(code)
                .map(mapper::toDto)
                .orElse(null);
    }

}
