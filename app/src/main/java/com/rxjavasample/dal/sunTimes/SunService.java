package com.rxjavasample.dal.sunTimes;

import com.rxjavasample.RetroService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.Sun;
import com.rxjavasample.models.SunHolder;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Josh on 6/12/2017.
 */

public class SunService {

    private ISun iSun;

    public SunService() {
        iSun = RetroService.createRetrofitService(ISun.class, "https://api.sunrise-sunset.org/");
    }

    public Flowable<Sun> getSun(City city){
        return iSun.getSunholder(city.getLatitude(), city.getLongitude())
                .subscribeOn(Schedulers.io())
                .map(new Function<SunHolder, Sun>() {
                    @Override
                    public Sun apply(@NonNull SunHolder sunHolder) throws Exception {
                        return sunHolder.getSun();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
