package com.rxjavasample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rxjavasample.dal.cities.CityService;
import com.rxjavasample.dal.states.StatesService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.State;
import com.rxjavasample.recyclerViews.states.StateRecyclerAdapter;

import org.reactivestreams.Publisher;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerState = (RecyclerView) findViewById(R.id.recyclerState);
        recyclerState.setLayoutManager(new LinearLayoutManager(this));

        new StatesService().getStates()
                .subscribe(new Consumer<ArrayList<State>>() {
                    @Override
                    public void accept(@NonNull ArrayList<State> states) throws Exception {
                        recyclerState.setAdapter(new StateRecyclerAdapter(states));
                    }
                });

    }
}
