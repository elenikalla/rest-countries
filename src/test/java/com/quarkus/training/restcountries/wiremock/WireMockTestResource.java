package com.quarkus.training.restcountries.wiremock;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import com.github.tomakehurst.wiremock.WireMockServer;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockTestResource implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(options().port(8089)); // start on 8089
        wireMockServer.start();

        wireMockServer.stubFor(get(urlPathEqualTo("/v3.1/all"))
                .withQueryParam("fields", equalTo("name,cca2,currencies"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                    [
                      {
                        "cca2": "GR",
                        "name": {"common": "Greece", "official": "Hellenic Republic"},
                        "currencies": {"EUR": {}}
                      },
                      {
                        "cca2": "FR",
                        "name": {"common": "France", "official": "French Republic"},
                        "currencies": {"EUR": {}}
                      }
                    ]
                """)));



        Map<String, String> config = new HashMap<>();
        config.put("quarkus.rest-client.restcountries.url", wireMockServer.baseUrl());
        return config;
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
