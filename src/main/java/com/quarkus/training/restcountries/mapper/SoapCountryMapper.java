package com.quarkus.training.restcountries.mapper;


import com.quarkus.training.restcountries.dto.LanguageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples.TLanguage;
import com.quarkus.training.restcountries.dto.FullCountryInfoDto;

@Mapper(componentModel = "cdi")
public interface SoapCountryMapper {

    @Mapping(source = "SISOCode", target = "isoCode")
    @Mapping(source = "SName", target = "name")
    @Mapping(source = "SCapitalCity", target = "capitalCity")
    @Mapping(source = "SPhoneCode", target = "phoneCode")
    @Mapping(source = "SContinentCode", target = "continentCode")
    @Mapping(source = "SCurrencyISOCode", target = "currencyCode")
    @Mapping(source = "languages.TLanguage", target = "languages")
    FullCountryInfoDto toDto(TCountryInfo country);

    @Mapping(source = "SISOCode", target = "isoCode")
    @Mapping(source = "SName", target = "name")
    LanguageDto languageToDto(TLanguage lang);

}
