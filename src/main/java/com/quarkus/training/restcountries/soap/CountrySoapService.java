package com.quarkus.training.restcountries.soap;

import jakarta.enterprise.context.ApplicationScoped;
import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;
import com.quarkus.training.restcountries.dto.FullCountryInfoDto;
import com.quarkus.training.restcountries.mapper.SoapCountryMapper;

@ApplicationScoped
public class CountrySoapService {

    private final CountryInfoServiceSoapType client;
    private final SoapCountryMapper mapper;

    public CountrySoapService(SoapCountryMapper mapper) {
        this.client = new CountryInfoService().getCountryInfoServiceSoap();
        this.mapper = mapper;
    }

    public FullCountryInfoDto getFullCountryInfo(String countryCode) {
        TCountryInfo response = client.fullCountryInfo(countryCode);
        return mapper.toDto(response);
    }
}
