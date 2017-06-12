package com.rxjavasample.recyclerViews.states;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rxjavasample.R;
import com.rxjavasample.models.State;

import java.util.ArrayList;

/**
 * Created by Josh on 6/12/2017.
 */

public class StateRecyclerAdapter extends RecyclerView.Adapter<StateViewHolder>{

    private ArrayList<State> states;

    public StateRecyclerAdapter(ArrayList<State> states) {
        this.states = states;
    }

    @Override
    public StateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StateViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_state, parent, false));
    }

    @Override
    public void onBindViewHolder(StateViewHolder holder, int position) {
        holder.bind(states.get(position));
    }

    @Override
    public int getItemCount() {
        return states.size();
    }
}
