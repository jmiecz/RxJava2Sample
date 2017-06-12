package com.rxjavasample.dal.states;

import com.rxjavasample.RetroService;
import com.rxjavasample.models.State;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Josh on 6/12/2017.
 */

public class StatesService {
    private IStates iStates;

    public StatesService() {
        iStates = RetroService.createRetrofitService(IStates.class, "https://gist.githubusercontent.com");
    }

    public Flowable<ArrayList<State>> getStates(){
        return iStates.getStates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
