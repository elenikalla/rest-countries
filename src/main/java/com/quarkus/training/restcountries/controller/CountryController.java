package com.quarkus.training.restcountries.controller;

import com.quarkus.training.restcountries.dto.CountryOutDto;
import com.quarkus.training.restcountries.service.CountryService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Countries", description = "Lookup countries by code and currency")
public class CountryController {

    @Inject
    CountryService service;

    @GET
    @Path("/currency/{currencyCode}")
    @Operation(
            summary = "Find countries by currency",
            description = "Returns all countries that use the given currency code (e.g., EUR, USD)."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Matching countries",
                    content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = CountryOutDto.class))
            ),
            @APIResponse(responseCode = "400", description = "Invalid currency code"),
            @APIResponse(responseCode = "404", description = "No countries found"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    public Response byCurrency(
            @Parameter(
                    description = "currency code (3 letters), e.g. 'EUR'",
                    required = true,
                    schema = @Schema(minLength = 3, maxLength = 3, pattern = "^[A-Za-z]{3}$")
            )
            @PathParam("currencyCode")
            @Size(min = 3, max = 3, message = "currencyCode must be 3 letters")
            @Pattern(regexp = "^[A-Za-z]{3}$", message = "currencyCode must contain only letters")
            String currencyCode
    ) {
        List<CountryOutDto> list = service.fetchByCurrency(currencyCode);
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/code/{countryCode}")
    @Operation(
            summary = "Find country by code",
            description = "Returns the country that matches the given code (e.g., GR, FR)."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Country found",
                    content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = CountryOutDto.class))
            ),
            @APIResponse(responseCode = "400", description = "Invalid country code"),
            @APIResponse(responseCode = "404", description = "Country not found"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    public Response byCode(
            @Parameter(
                    description = "Code (2 letters), e.g. 'GR'",
                    required = true,
                    schema = @Schema(minLength = 2, maxLength = 2, pattern = "^[A-Za-z]{2}$")
            )
            @PathParam("countryCode")
            @Size(min = 2, max = 2, message = "countryCode must be 2 letters")
            @Pattern(regexp = "^[A-Za-z]{2}$", message = "countryCode must contain only letters")
            String countryCode
    ) {
        CountryOutDto dto = service.fetchByCode(countryCode);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(List.of(dto)).build();
    }
}
