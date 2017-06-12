package com.rxjavasample.dal.cities;

import com.rxjavasample.models.City;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Josh on 6/12/2017.
 */

public interface ICities {

    @GET("geodata/city_links_for_state_of/{stateCode}.json")
    Flowable<ArrayList<City>> getCites(@Path("stateCode") String stateCode);
}
