package com.quarkus.training.restcountries.service;

import com.quarkus.training.restcountries.mapper.CountryMapper;
import com.quarkus.training.restcountries.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceUnitTest {

    @Mock
    CountryRepository repo;

    @Mock
    CountryMapper mapper;

    @InjectMocks
    CountryService service;

    @Test
    void fetchByCode_returnsNull_whenRepositoryReturnsEmptyOptional() {
        when(repo.findByCodePanache("ZZ")).thenReturn(Optional.empty());
        var result = service.fetchByCode("ZZ");
        assertNull(result, "when repository returns empty, service should return null");
    }
}
