package com.rxjavasample.dal.weather;

import com.rxjavasample.RetroService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.MetaWeatherObject;
import com.rxjavasample.models.Weather;

import org.reactivestreams.Publisher;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Josh on 6/12/2017.
 */

public class WeatherService {
    private IWeather iWeather;

    public WeatherService() {
        iWeather = RetroService.createRetrofitService(IWeather.class, "https://www.metaweather.com/");
    }

    private Flowable<MetaWeatherObject> getMetaWeatherObject(final City city){
        return iWeather.getWeatherIDs(city.getLatLng())
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<ArrayList<MetaWeatherObject>, Publisher<MetaWeatherObject>>() {
                    @Override
                    public Publisher<MetaWeatherObject> apply(@NonNull ArrayList<MetaWeatherObject> metaWeatherObjects) throws Exception {
                        return Flowable.fromIterable(metaWeatherObjects);
                    }
                })
                .filter(new Predicate<MetaWeatherObject>() {
                    @Override
                    public boolean test(@NonNull MetaWeatherObject metaWeatherObject) throws Exception {
                        return metaWeatherObject.getName().equalsIgnoreCase(city.getName());
                    }
                })
                .firstOrError()
                .toFlowable();
    }

    public Flowable<Weather> getWeather(City city){
        return getMetaWeatherObject(city)
                .flatMap(new Function<MetaWeatherObject, Publisher<Weather>>() {
                    @Override
                    public Publisher<Weather> apply(@NonNull MetaWeatherObject metaWeatherObject) throws Exception {
                        return iWeather.getWeather(metaWeatherObject.getId());
                    }
                });
    }
}
