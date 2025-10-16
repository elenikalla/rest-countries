package com.quarkus.training.restcountries.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInDto {

    @JsonProperty("cca2")
    private String code;

    @JsonProperty("name")
    private Map<String, Object> nameData;

    @JsonProperty("currencies")
    private Map<String, Object> currencyData;

    public String getCommonName() {
        return nameData.get("common").toString();
    }

    public String getOfficialName() {
        return nameData.get("official").toString();
    }

    public Set<String> getCurrencyCodes() {
        return currencyData.keySet();
    }
}
