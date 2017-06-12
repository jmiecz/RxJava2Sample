package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class Sun {

    @JsonProperty("sunrise")
    private String sunRise;

    @JsonProperty("sunset")
    private String sunSet;

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }
}
