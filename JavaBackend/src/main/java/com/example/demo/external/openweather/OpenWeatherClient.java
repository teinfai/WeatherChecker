package com.example.demo.external.openweather;

import com.example.demo.dto.OpenCegaGeoResponseDto;
import com.example.demo.external.openweather.model.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenWeatherClient {

    private final RestTemplate restTemplate;

    @Value("${openweather.api.url}")
    private String baseUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenWeatherResponse getWeatherByCoordinates(double lat, double lng) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lng)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        return restTemplate.getForObject(url, OpenWeatherResponse.class);
    }
}
