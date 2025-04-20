package com.example.demo.service;

import com.example.demo.dto.LocationRequestDto;
import com.example.demo.dto.OpenWeatherDto;
import com.example.demo.dto.WeatherCegaResponseDto;
import com.example.demo.exception.WeatherTrackerException;
import com.example.demo.external.opencega.OpenCegaClient;
import com.example.demo.external.opencega.model.OpenCegaResponse;
import com.example.demo.external.openweather.OpenWeatherClient;
import com.example.demo.external.openweather.model.OpenWeatherResponse;
import com.example.demo.repository.WeatherTrackerRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherTrackerService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherTrackerService.class);

    private OpenCegaClient openCegaClient;
    private OpenWeatherClient openWeatherClient;
    private WeatherTrackerRepository weatherTrackerRepository;

    public WeatherTrackerService (OpenCegaClient openCegaClient,OpenWeatherClient openWeatherClient,WeatherTrackerRepository weatherTrackerRepository) {
        this.openCegaClient = openCegaClient;
        this.openWeatherClient = openWeatherClient;
        this.weatherTrackerRepository = weatherTrackerRepository;
    }

    private boolean isInvalid(String input) {
        return "string".equalsIgnoreCase(input.trim());
    }

    public WeatherCegaResponseDto getWeatherInfo(LocationRequestDto request) {

        String address = request.getAddress();
        String city = isInvalid(request.getCity()) ? "" : request.getCity().trim();
        String country = isInvalid(request.getCountry()) ? "" : request.getCountry().trim();

        OpenCegaResponse geoResponse = openCegaClient.getCoordinates(address, country, city);

        if (geoResponse == null || geoResponse.getResults() == null || geoResponse.getResults().isEmpty()) {
            throw new WeatherTrackerException("Could not find coordinates for the given input.");
        }

        WeatherCegaResponseDto dto = new WeatherCegaResponseDto();

        if (geoResponse != null) {

            double lat = geoResponse.getLatitude();
            double lng = geoResponse.getLongitude();

            OpenWeatherResponse weatherResponse = openWeatherClient.getWeatherByCoordinates(lat,lng);

            dto.setAddress(request.getAddress());
            dto.setCity(request.getCity());
            dto.setCountry(request.getCountry());
            dto.setWeather(weatherResponse.getWeather().get(0).getMain());
            dto.setLatitude(lat);
            dto.setLongitude(lng);
            dto.setWindSpeed(weatherResponse.getWind().getSpeed());
            dto.setDescription(weatherResponse.getWeather().get(0).getDescription());
            dto.setTemperature(weatherResponse.getMain().getTemp());
            dto.setHumidity(weatherResponse.getMain().getHumidity());
            dto.setFeelsLike(weatherResponse.getMain().getFeels_like());

        }
        return dto;

    }

}

