package com.example.demo.external.opencega;

import com.example.demo.external.opencega.model.OpenCegaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenCegaClient {

    private final RestTemplate restTemplate;

    @Value("${opencega.api.url}")
    private String baseUrl;

    @Value("${opencega.api.key}")
    private String apiKey;

    public OpenCegaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenCegaResponse getCoordinates(String address, String city, String country) {
        String fullAddress = address + ", " + city + ", " + country;

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/geocode/v1/json")
                .queryParam("q", fullAddress)
                .queryParam("key", apiKey)
                .build()
                .toUriString();

        return restTemplate.getForObject(url, OpenCegaResponse.class);
    }
}
