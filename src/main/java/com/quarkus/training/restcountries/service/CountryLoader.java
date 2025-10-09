package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.client.CountryClient;
import com.quarkus.training.restcountries.dto.CountryInDto;
import com.quarkus.training.restcountries.model.Country;
import com.quarkus.training.restcountries.repository.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.runtime.StartupEvent;

import java.util.*;
import java.util.stream.Collectors;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;


@ApplicationScoped
public class CountryLoader {

    @Inject @RestClient
    CountryClient restCountriesClient;

    @Inject
    CountryRepository countryRepository;
    @Inject
    CountryService countryService;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        List<CountryInDto> list = restCountriesClient.getAllCountries("name,cca2,currencies");

        List<Country> newCountries = list.stream()
                .map(countryService::toEntity)
                .filter(c -> countryRepository.findByIdOptional(c.code).isEmpty())
                .collect(Collectors.toList());

        countryRepository.persist(newCountries);
    }

}








