package com.quarkus.training.restcountries.client;

import com.quarkus.training.restcountries.dto.CountryInDto;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(configKey = "restcountries")
@Path("/v3.1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountryClient {

    @GET
    @Path("/all")
    Uni<List<CountryInDto>> getAllCountries(@QueryParam("fields") String fields);

}
