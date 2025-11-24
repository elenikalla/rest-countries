package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.dto.CountryOutDto;
import com.quarkus.training.restcountries.mapper.CountryMapper;
import com.quarkus.training.restcountries.repository.CountryRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
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
    @WithSession
    public Uni<List<CountryOutDto>> fetchByCurrency(String currency) {
        return repo.findByCurrencyPanache(currency)
                .onItem()
                .transform(dtos->dtos.stream().map(mapper::toDto).toList());

    }
    @WithSession
    public Uni<CountryOutDto> fetchByCode(String code) {
        return repo.findByCodePanache(code)
                .map(mapper::toDto);

    }

}
