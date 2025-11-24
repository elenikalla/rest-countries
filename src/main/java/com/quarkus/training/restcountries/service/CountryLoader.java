package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.client.CountryClient;
import com.quarkus.training.restcountries.mapper.CountryMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.VertxContextSupport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.hibernate.reactive.mutiny.Mutiny;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CountryLoader {

    private final CountryClient restCountriesClient;
    private final CountryMapper countryMapper;

    public CountryLoader(@RestClient CountryClient restCountriesClient,
                         CountryMapper countryMapper) {
        this.restCountriesClient = restCountriesClient;
        this.countryMapper = countryMapper;
    }

    void onStart(@Observes StartupEvent event, Mutiny.SessionFactory sf) throws Throwable {
        VertxContextSupport.subscribeAndAwait(() ->
                sf.withTransaction(session ->
                        restCountriesClient.getAllCountries("name,cca2,currencies")
                                .onItem().transform(dtos -> dtos.stream().map(countryMapper::toEntity).toList())
                                .call(entities -> session.persistAll(entities.toArray()))
                                .replaceWithVoid()
                )
        );
        Log.info("Countries loaded on startup");
    }
}
