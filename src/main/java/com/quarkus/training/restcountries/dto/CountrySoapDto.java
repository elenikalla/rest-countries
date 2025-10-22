package com.quarkus.training.restcountries.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CountrySoapDto {

    @XmlElement
    private String code;

    @XmlElement
    private String commonName;

    @XmlElement
    private String officialName;

    @XmlElementWrapper(name = "currencies")
    @XmlElement(name = "currency")
    private Set<String> currencies;

}

