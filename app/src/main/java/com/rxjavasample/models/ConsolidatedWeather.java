package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class ConsolidatedWeather {

    @JsonProperty("weather_state_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
