package com.rxjavasample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Josh on 6/12/2017.
 */

public class State implements Parcelable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("abbreviation")
    private String abbreviation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.abbreviation);
    }

    public State() {
    }

    protected State(Parcel in) {
        this.name = in.readString();
        this.abbreviation = in.readString();
    }

    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
        @Override
        public State createFromParcel(Parcel source) {
            return new State(source);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
