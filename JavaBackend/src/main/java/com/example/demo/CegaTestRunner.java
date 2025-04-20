//package com.example.demo;
//
//import com.example.demo.external.opencega.OpenCegaClient;
//import com.example.demo.external.opencega.model.OpenCegaResponse;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CegaTestRunner implements CommandLineRunner {
//
//    private final OpenCegaClient openCegaClient;
//
//    public CegaTestRunner(OpenCegaClient openCegaClient) {
//        this.openCegaClient = openCegaClient;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // dummy input
//        String address = "1600 Amphitheatre Parkway,,,,";
//        String city = "";
//        String country = "";
//
//        OpenCegaResponse response = openCegaClient.getCoordinates(address, city, country);
//        System.out.println("Latitude: " + response.getLatitude());
//        System.out.println("Longitude: " + response.getLongitude());
//
//    }
//}
//
