package com.rxjavasample.dal.cities;

import com.rxjavasample.RetroService;
import com.rxjavasample.dal.sunTimes.SunService;
import com.rxjavasample.dal.weather.WeatherService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.State;
import com.rxjavasample.models.Sun;
import com.rxjavasample.models.Weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Josh on 6/12/2017.
 */

public class CityService {

    private ICities iCities;

    public CityService() {
        iCities = RetroService.createRetrofitService(ICities.class, "http://api.sba.gov/");
    }

    public Flowable<ArrayList<City>> getCities(State state){
        return iCities.getCites(state.getAbbreviation())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ArrayList<City>> getSortedCities(State state){
        return getCities(state)
                .map(new Function<ArrayList<City>, ArrayList<City>>() {
                    @Override
                    public ArrayList<City> apply(@NonNull ArrayList<City> cities) throws Exception {
                        Collections.sort(cities, new Comparator<City>() {
                            @Override
                            public int compare(City city, City city2) {
                                return city.getName().compareTo(city2.getName());
                            }
                        });
                        return cities;
                    }
                });
    }

    public Flowable<City> getCityDetails(City city){
        return Flowable.zip(Flowable.just(city),
                new WeatherService().getWeather(city),
                new SunService().getSun(city),
                new Function3<City, Weather, Sun, City>() {
                    @Override
                    public City apply(@NonNull City city, @NonNull Weather weather, @NonNull Sun sun) throws Exception {
                        city.setWeather(weather);
                        city.setSun(sun);

                        return city;
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
