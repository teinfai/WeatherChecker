package com.example.demo.external.opencega.model;

import java.util.List;

//public class OpenCegaResponse {
//
//    private List<Result> results;
//
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }
//
//    public double getLatitude() {
//        if (results != null && !results.isEmpty() && results.get(0).getGeometry() != null) {
//            return results.get(0).getGeometry().getLat();
//        }
//        throw new IllegalStateException("No latitude available in OpenCegaResponse.");
//    }
//
//    public double getLongitude() {
//        if (results != null && !results.isEmpty() && results.get(0).getGeometry() != null) {
//            return results.get(0).getGeometry().getLng();
//        }
//        throw new IllegalStateException("No longitude available in OpenCegaResponse.");
//    }
//}

public class OpenCegaResponse {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public double getLatitude() {
        if (results != null && !results.isEmpty() && results.get(0).getGeometry() != null) {
            return results.get(0).getGeometry().getLat();
        }
        throw new IllegalStateException("No latitude available in OpenCegaResponse.");
    }

    public double getLongitude() {
        if (results != null && !results.isEmpty() && results.get(0).getGeometry() != null) {
            return results.get(0).getGeometry().getLng();
        }
        throw new IllegalStateException("No longitude available in OpenCegaResponse.");
    }

}
