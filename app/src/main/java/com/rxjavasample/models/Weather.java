package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Josh on 6/12/2017.
 */

public class Weather {

    @JsonProperty("consolidated_weather")
    private ArrayList<ConsolidatedWeather> consolidatedWeathers = new ArrayList<>();

    public ArrayList<ConsolidatedWeather> getConsolidatedWeathers() {
        return consolidatedWeathers;
    }

    public void setConsolidatedWeathers(ArrayList<ConsolidatedWeather> consolidatedWeathers) {
        this.consolidatedWeathers = consolidatedWeathers;
    }

    @JsonIgnore
    public String getWeather(){
        return consolidatedWeathers.get(0).getName();
    }
}
