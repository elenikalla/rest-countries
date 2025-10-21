package com.quarkus.training.restcountries.mapper;
import com.quarkus.training.restcountries.dto.CountryInDto;
import com.quarkus.training.restcountries.dto.CountryOutDto;
import com.quarkus.training.restcountries.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "cdi")
public interface CountryMapper {

    @Mapping(target = "commonName", expression = "java(dto.getCommonName())")
    @Mapping(target = "officialName", expression = "java(dto.getOfficialName())")
    @Mapping(target = "currencies", expression = "java(dto.getCurrencyCodes())")
    Country toEntity(CountryInDto dto);


    CountryOutDto toDto(Country entity);
}




