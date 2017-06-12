package com.rxjavasample.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class MetaWeatherObject {

    @JsonProperty("title")
    private String name;

    @JsonProperty("woeid")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
