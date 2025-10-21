package com.quarkus.training.restcountries.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullCountryInfoDto {

    @JsonProperty("ISOCode")
    private String isoCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("capitalCity")
    private String capitalCity;

    @JsonProperty("phoneCode")
    private String phoneCode;

    @JsonProperty("continentCode")
    private String continentCode;

    @JsonProperty("currencyCode")
    private String currencyCode;

    @JsonProperty("languages")
    private List<LanguageDto> languages;


}
