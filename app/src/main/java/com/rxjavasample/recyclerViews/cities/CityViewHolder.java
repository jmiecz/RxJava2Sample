package com.rxjavasample.recyclerViews.cities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjavasample.R;
import com.rxjavasample.dal.cities.CityService;
import com.rxjavasample.models.City;
import com.rxjavasample.models.State;

import org.reactivestreams.Subscription;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Josh on 6/12/2017.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCity;

    public CityViewHolder(View itemView) {
        super(itemView);

        txtCity = (TextView) itemView.findViewById(R.id.txtState);
    }

    public void bind(final City city){
        txtCity.setText(city.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city.hasData()){
                    showMessage(city);
                }else{
                    new CityService().getCityDetails(city)
                            .doOnSubscribe(new Consumer<Subscription>() {
                                @Override
                                public void accept(@NonNull Subscription subscription) throws Exception {
                                    Toast.makeText(itemView.getContext(), "Grabbing data", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .subscribe(new Consumer<City>() {
                                @Override
                                public void accept(@NonNull City city) throws Exception {
                                    showMessage(city);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    Toast.makeText(itemView.getContext(), "No data", Toast.LENGTH_SHORT).show();
                                    throwable.printStackTrace();
                                }
                            });
                }
            }
        });
    }

    private void showMessage(City city){
        String message = "Weather: " + city.getWeather().getWeather()
                + "\n" + "Sunrise: " + city.getSun().getSunRise()
                + "\n" + "Sunset: " + city.getSun().getSunSet();
        Toast.makeText(itemView.getContext(), message, Toast.LENGTH_LONG).show();
    }
}
