package com.example.asteroid.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asteroid.model.MarsSol;
import com.example.asteroid.R;

import java.util.List;

public class MarsWeatherAdapter extends RecyclerView.Adapter<MarsWeatherAdapter.ViewHolder> {
    private List<MarsSol> solList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView solNumber, temperature, pressure;
        public ViewHolder(View v) {
            super(v);
            solNumber = v.findViewById(R.id.solNumber);
            temperature = v.findViewById(R.id.temperature);
            pressure = v.findViewById(R.id.pressure);
        }
    }

    public MarsWeatherAdapter(List<MarsSol> list) {
        solList = list;
    }

    @Override
    public MarsWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sol, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MarsSol sol = solList.get(position);
        holder.solNumber.setText("Sol nº" + sol.getSolNumber());
        holder.temperature.setText("Température :" + sol.getAverageTemp());
        holder.pressure.setText("Pression :" + sol.getAveragePressure());
    }

    @Override
    public int getItemCount() {
        return solList.size();
    }
}
