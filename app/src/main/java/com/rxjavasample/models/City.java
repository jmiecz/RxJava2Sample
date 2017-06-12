package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class City {

    @JsonProperty("name")
    private String name;

    @JsonProperty("primary_latitude")
    private String latitude;

    @JsonProperty("primary_longitude")
    private String longitude;

    @JsonIgnore
    private Weather weather;

    @JsonIgnore
    private Sun sun;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonIgnore
    public String getLatLng(){
        return getLatitude() + "," + getLongitude();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    public boolean hasData(){
        return sun != null && weather != null;
    }
}
