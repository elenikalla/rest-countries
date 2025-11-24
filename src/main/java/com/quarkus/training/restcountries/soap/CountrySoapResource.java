package com.quarkus.training.restcountries.soap;

import com.quarkus.training.restcountries.dto.CountrySoapDto;
import com.quarkus.training.restcountries.mapper.SoapCountryMapper;
import com.quarkus.training.restcountries.service.CountryService;
import io.quarkus.vertx.VertxContextSupport;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Uni;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "CountryService",
        portName = "CountryServicePort",
        targetNamespace = "http://com.quarkus.training.restcountries/"
)
public class CountrySoapResource {
    CountryService service;
    SoapCountryMapper soapMapper;
    CountrySoapResource(CountryService service,SoapCountryMapper soapMapper) {
        this.service = service;
        this.soapMapper = soapMapper;
    }
    @WebMethod
    public List<CountrySoapDto> getCountriesByCurrency(
            @WebParam(name = "currencyCode") String currencyCode) throws Throwable {

        return VertxContextSupport.subscribeAndAwait(() ->
                service.fetchByCurrency(currencyCode)
                        .map(list -> list.stream()
                                .map(soapMapper::toSoapDto)
                                .toList())
        );
    }
}
