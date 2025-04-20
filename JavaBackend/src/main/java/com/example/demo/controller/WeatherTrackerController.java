package com.example.demo.controller;

import com.example.demo.dto.LocationRequestDto;
import com.example.demo.dto.WeatherCegaResponseDto;
import com.example.demo.service.WeatherTrackerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weathertracker")
@Api(value = "User API", tags = "User API")
public class WeatherTrackerController {

    @Autowired
    private WeatherTrackerService weatherTrackerService;


    @PostMapping("/info")
    @ApiOperation(value = "Get weather by location")
    public ResponseEntity<?> getWeatherInfo(@RequestBody LocationRequestDto request) {
        if ("string".equalsIgnoreCase(request.getAddress()) || request.getAddress().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid address input: 'string' is not allowed");
        }

        WeatherCegaResponseDto response = weatherTrackerService.getWeatherInfo(request);
        return ResponseEntity.ok(response);
    }
}