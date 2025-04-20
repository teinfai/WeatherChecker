package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Location input fields for weather query")
public class LocationRequestDto {

    @ApiModelProperty(value = "Address", example = "")
    private String address;

    @ApiModelProperty(value = "City (optional)", example = "")
    private String city;

    @ApiModelProperty(value = "Country (optional)", example = "")
    private String country;

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
