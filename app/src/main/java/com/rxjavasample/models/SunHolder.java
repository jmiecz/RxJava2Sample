package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class SunHolder {

    @JsonProperty("results")
    private Sun sun;

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }
}
