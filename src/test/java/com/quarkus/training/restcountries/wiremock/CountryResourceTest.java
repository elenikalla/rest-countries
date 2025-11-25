package com.quarkus.training.restcountries.wiremock;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@QuarkusTestResource(WireMockTestResource.class)
public class CountryResourceTest {
//
//    @Test
//    void testCountriesByCurrency() {
//        given()
//                .when().get("/countries/currency/EUR")
//                .then()
//                .statusCode(200)
//                .body("$.size()", is(2));
//    }
//
//    @Test
//    void testCountryByCode() {
//        given()
//                .when().get("/countries/code/GR")
//                .then()
//                .statusCode(200)
//                .body("$.size()", is(1))
//                .body("[0].commonName", is("Greece"));
//    }
}
