package com.quarkus.training.restcountries.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends PanacheEntityBase {

    @Id
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String commonName;

    @Column(nullable = false)
    private String officialName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "country_currencies", joinColumns = @JoinColumn(name = "country_code"))
    @Column(name = "currency")
    private Set<String> currencies = new HashSet<>();
}
