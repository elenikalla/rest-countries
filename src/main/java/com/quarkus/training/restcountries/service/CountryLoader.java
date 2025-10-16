package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.client.CountryClient;
import com.quarkus.training.restcountries.dto.CountryInDto;
import com.quarkus.training.restcountries.mapper.CountryMapper;
import com.quarkus.training.restcountries.model.Country;
import com.quarkus.training.restcountries.repository.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountryLoader {

    private final CountryClient restCountriesClient;
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryLoader(@RestClient CountryClient restCountriesClient,
                         CountryRepository countryRepository,
                         CountryMapper countryMapper) {
        this.restCountriesClient = restCountriesClient;
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        List<CountryInDto> list = restCountriesClient.getAllCountries("name,cca2,currencies");

        List<Country> newCountries = list.stream()
                .map(countryMapper::toEntity)
                .filter(c -> countryRepository.findByIdOptional(c.getCode()).isEmpty())
                .collect(Collectors.toList());

        if (!newCountries.isEmpty()) {
            countryRepository.persist(newCountries);
        }
    }
}
