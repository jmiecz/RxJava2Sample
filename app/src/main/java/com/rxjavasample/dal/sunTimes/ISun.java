package com.rxjavasample.dal.sunTimes;

import com.rxjavasample.models.SunHolder;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Josh on 6/12/2017.
 */

public interface ISun {

    @GET("json")
    Flowable<SunHolder> getSunholder(@Query("lat") String latitude, @Query("lng") String longitude);
}
