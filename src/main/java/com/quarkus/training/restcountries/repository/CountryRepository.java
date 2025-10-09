package com.quarkus.training.restcountries.repository;

import com.quarkus.training.restcountries.model.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CountryRepository implements PanacheRepositoryBase<Country,String> {


    public Optional<Country> findByCodePanache(String code) {
        return findByIdOptional(code);
    }

    public List<Country> findByCurrencyPanache(String currencyCode) {
        return list("select c from Country c join c.currencies cur where cur = ?1", currencyCode);
    }
}