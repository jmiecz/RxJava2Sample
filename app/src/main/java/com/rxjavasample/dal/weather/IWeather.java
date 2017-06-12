package com.rxjavasample.dal.weather;

import com.rxjavasample.models.MetaWeatherObject;
import com.rxjavasample.models.Weather;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Josh on 6/12/2017.
 */

public interface IWeather {

    @GET("api/location/search/")
    Flowable<ArrayList<MetaWeatherObject>> getWeatherIDs(@Query("lattlong") String latLng);

    @GET("api/location/{weatherID}")
    Flowable<Weather> getWeather(@Path("weatherID") int weatherID);
}
