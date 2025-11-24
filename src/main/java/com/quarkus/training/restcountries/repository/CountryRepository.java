package com.quarkus.training.restcountries.repository;

import com.quarkus.training.restcountries.model.Country;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {


    public Uni<Country> findByCodePanache(String code) {
        return find("code", code).firstResult();
    }

    public Uni<List<Country>> findByCurrencyPanache(String currencyCode) {
        return list("select c from Country c join c.currencies cur where cur = ?1", currencyCode);
    }
}