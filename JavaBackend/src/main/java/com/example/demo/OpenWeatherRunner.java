//package com.example.demo;
//
//import com.example.demo.dto.OpenWeatherDto;
//import com.example.demo.external.opencega.model.OpenCegaResponse;
//import com.example.demo.external.openweather.OpenWeatherClient;
//import com.example.demo.external.openweather.model.OpenWeatherResponse;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OpenWeatherRunner implements CommandLineRunner {
//
//    private final OpenWeatherClient openWeatherClient;
//
//    public OpenWeatherRunner(OpenWeatherClient openWeatherClient) {
//        this.openWeatherClient = openWeatherClient;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // dummy input
//        double lat = 3.025340;
//        double lng = 101.617767;
//
//        OpenWeatherResponse response = openWeatherClient.getWeatherByCoordinates(lat, lng);
////        System.out.println("getSpeed: " + response.getWind().getSpeed());
////        System.out.println("getHumidity: " + response.getMain().getHumidity());
////        System.out.println("getTemp: " + response.getMain().getTemp());
////        System.out.println("getFeels_like: " + response.getMain().getFeels_like());
////        System.out.println("getFeels_like: " + response.getWeather().get(0).getMain());
////        System.out.println("getFeels_like: " + response.getWeather().get(0).getDescription());
//    }
//}
//
