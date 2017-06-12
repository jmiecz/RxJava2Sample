package com.rxjavasample.recyclerViews.states;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rxjavasample.CityActivity;
import com.rxjavasample.R;
import com.rxjavasample.models.State;

/**
 * Created by Josh on 6/12/2017.
 */

public class StateViewHolder extends RecyclerView.ViewHolder {

    private TextView txtState;

    public StateViewHolder(View itemView) {
        super(itemView);

        txtState = (TextView) itemView.findViewById(R.id.txtState);
    }

    public void bind(final State state){
        txtState.setText(state.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityActivity.startCityActivity(itemView.getContext(), state);
            }
        });
    }
}
