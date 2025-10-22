package com.quarkus.training.restcountries.soap;

import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;
import com.quarkus.training.restcountries.dto.FullCountryInfoDto;
import com.quarkus.training.restcountries.mapper.SoapCountryMapper;

@ApplicationScoped
public class CountrySoapService {
    private final CountryInfoServiceSoapType client;
    private final SoapCountryMapper mapper;

    @Inject
    public CountrySoapService(SoapCountryMapper mapper,
                              @CXFClient("CountryInfoServiceSoapType") CountryInfoServiceSoapType client) {
        this.client = client;
        this.mapper = mapper;
    }

    public FullCountryInfoDto getFullCountryInfo(String countryCode) {
        TCountryInfo response = client.fullCountryInfo(countryCode);
        return mapper.toDto(response);
    }
}
