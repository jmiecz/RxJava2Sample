package com.rxjavasample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rxjavasample.dal.cities.CityService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.State;
import com.rxjavasample.recyclerViews.cities.CityRecyclerAdapter;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Josh on 6/12/2017.
 */

public class CityActivity extends AppCompatActivity {

    private static final String STATE = "state";

    private RecyclerView recyclerCity;

    private State state;

    public static void startCityActivity(Context context, State state){
        Intent intent = new Intent(context, CityActivity.class);
        intent.putExtra(STATE, state);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent() != null){
            state = getIntent().getExtras().getParcelable(STATE);
        }

        recyclerCity = (RecyclerView) findViewById(R.id.recyclerState);
        recyclerCity.setLayoutManager(new LinearLayoutManager(this));

        new CityService().getSortedCities(state)
                .subscribe(new Consumer<ArrayList<City>>() {
                    @Override
                    public void accept(@NonNull ArrayList<City> cities) throws Exception {
                        recyclerCity.setAdapter(new CityRecyclerAdapter(cities));
                    }
                });
    }
}
