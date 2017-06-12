package com.rxjavasample.recyclerViews.cities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rxjavasample.R;
import com.rxjavasample.models.City;
import com.rxjavasample.models.State;

import java.util.ArrayList;

/**
 * Created by Josh on 6/12/2017.
 */

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityViewHolder>{

    private ArrayList<City> cities;

    public CityRecyclerAdapter(ArrayList<City> cities) {
        this.cities = cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_state, parent, false));
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
