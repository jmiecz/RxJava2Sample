package com.rxjavasample.dal.states;

import com.rxjavasample.models.State;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Josh on 6/12/2017.
 */

public interface IStates {

    @GET("mshafrir/2646763/raw/8b0dbb93521f5d6889502305335104218454c2bf/states_titlecase.json")
    Flowable<ArrayList<State>> getStates();

    //If you want to include the status code, wrap Response<> around your object
    //Flowable<Response<ArrayList<State>>> getStates();
}
